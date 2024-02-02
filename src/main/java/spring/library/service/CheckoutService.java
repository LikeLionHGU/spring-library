package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Checkout;
import spring.library.domain.Member;
import spring.library.controller.request.CheckoutRequest;
import spring.library.controller.response.checkout.CheckoutBookListResponse;
import spring.library.controller.response.checkout.CheckoutBookResponse;
import spring.library.controller.response.checkout.CheckoutHistoryListResponse;
import spring.library.controller.response.checkout.CheckoutHistoryResponse;
import spring.library.dto.CheckoutDto;
import spring.library.exception.BookNotFoundException;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.BookRepository;
import spring.library.repository.CheckoutRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    public void checkoutBook(Long bookId, CheckoutDto checkoutDto) {
        Member member = memberRepository.findById(checkoutDto.getMemberId()).orElseThrow(() -> new MemberNotFoundException());
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException());
        if(book.getStatus().equals("대출가능")){
            Checkout checkout = Checkout.from(book, member);
            book.updateStatus("대출중");
            bookRepository.save(book);
            checkoutRepository.save(checkout);
        }
        else {
            throw new RuntimeException("대출 불가능한 책입니다.");
        }
    }

    public CheckoutBookListResponse getCheckoutList(Long memberId){
        List<Checkout> checkouts = checkoutRepository.findByMember_MemberId(memberId);
        List<CheckoutBookResponse> bookResponses = checkouts.stream()
                .filter(checkout -> checkout.getBook() != null && checkout.getMember() != null && !checkout.isReturned())
                .map(CheckoutBookResponse::new)
                .collect(Collectors.toList());
        return new CheckoutBookListResponse(bookResponses);

    }

    public CheckoutHistoryListResponse getHistoryList(Long memberId){
        List<Checkout> checkouts = checkoutRepository.findByMember_MemberId(memberId);
        List<CheckoutHistoryResponse> bookResponses = checkouts.stream()
                .filter(checkout -> checkout.getBook() != null && checkout.getMember() != null)
                .map(CheckoutHistoryResponse::new)
                .collect(Collectors.toList());
        return new CheckoutHistoryListResponse(bookResponses);
    }

    public void returnBook(Long checkOutId, CheckoutDto checkoutDto) {
        Checkout checkout = checkoutRepository.findByCheckoutIdAndMember_MemberId(checkOutId, checkoutDto.getMemberId());
        Book book = checkout.getBook();
        if(checkout.isReturned()){
            throw new RuntimeException("이미 반납된 책입니다.");
        }

        book.updateStatus("대출가능");
        checkout.updateIsReturned(true);

        bookRepository.save(book);
        checkoutRepository.save(checkout);
    }

    public void renewalBook(Long checkOutId, CheckoutDto checkoutDto) {
        Checkout checkout = checkoutRepository.findByCheckoutIdAndMember_MemberId(checkOutId, checkoutDto.getMemberId());

        if(checkout.getRenewalCount() >= 1) {
            throw new RuntimeException("연장 횟수 초과");
        }
        if(checkout.getCheckOutDate() == LocalDate.now().toString()) {
            throw new RuntimeException("반납 예정일 당일에만 연장 가능");
        }
        if(checkout.isReturned()){
            throw new RuntimeException("반납된 책은 연장 불가");
        }
        checkout.updateRenewalCount(checkout.getRenewalCount() + 1);
        checkoutRepository.save(checkout);
    }
}
