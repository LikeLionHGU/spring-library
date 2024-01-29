package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Book;
import spring.library.domain.Checkout;
import spring.library.domain.Member;
import spring.library.exception.BookIsUnavailableException;
import spring.library.exception.IdPresenceException;
import spring.library.repository.BookRepository;
import spring.library.repository.CheckoutRepository;
import spring.library.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckoutService {

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
}