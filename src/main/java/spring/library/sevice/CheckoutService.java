package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Book;
import spring.library.domain.Checkout;
import spring.library.domain.Member;
import spring.library.dto.CheckoutDto;
import spring.library.exception.BookIsUnavailableException;
import spring.library.exception.IdPresenceException;
import spring.library.repository.BookRepository;
import spring.library.repository.CheckoutRepository;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckoutService {
    private static final int EXTENSION_DAY = 5;
    private static final int MAX_RENEWAL_COUNT = 1;
    private static final int RENEWAL_DATE_BEFORE_DUE_DATE = 0;

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final CheckoutRepository checkoutRepository;

    public void checkoutBook(Long bookId, Long memberId) {
        Book book = ValidateBookPresence(bookId);
        validateBookIsAvailable(book);
        Member member = ValidateMemberPresence(memberId);
        checkoutRepository.save(Checkout.from(member, book));
    }

    public void validateBookIsAvailable(Book book) {
        checkoutRepository.findByBookAndIsReturned(book, false)
                .ifPresent(checkout -> {
                    throw new BookIsUnavailableException();
                });
    }

    public Book ValidateBookPresence(Long bookId) {
        System.out.println("bookId = " + bookId);
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IdPresenceException("존재하지 않는 도서입니다."));
    }

    public Member ValidateMemberPresence(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IdPresenceException("존재하지 않는 회원입니다."));
    }

    public List<CheckoutDto> getCheckouts(Long memberId) {
        ValidateMemberPresence(memberId);
        return checkoutRepository.findNotReturnedByMemberIdFetchMember(memberId).stream()
                .map(CheckoutDto::from)
                .toList();
    }

    public List<CheckoutDto> getCheckoutsHistory(Long memberId) {
        ValidateMemberPresence(memberId);
        return checkoutRepository.findByMemberIdFetchMember(memberId).stream()
                .map(CheckoutDto::from)
                .toList();
    }

    public void returnBook(Long checkoutId) {
        Checkout checkout = ValidateCheckoutPresence(checkoutId);
        checkout.validateIsReturned();
        checkout.returnBook();
    }

    public Checkout ValidateCheckoutPresence(Long checkoutId) {
        return checkoutRepository.findById(checkoutId)
                .orElseThrow(() -> new IdPresenceException("존재하지 않는 대출입니다."));
    }

    public void extendCheckout(Long checkoutId) {
        Checkout checkout = ValidateCheckoutPresence(checkoutId);
        checkout.validateIsReturned();
        checkout.validateRenewalCount(MAX_RENEWAL_COUNT);
        checkout.validateRenewalPeriod(RENEWAL_DATE_BEFORE_DUE_DATE);
        checkout.extendCheckout(EXTENSION_DAY);
    }
}