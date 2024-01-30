package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Checkout;
import spring.library.domain.Member;
import spring.library.dto.request.CheckoutRequest;
import spring.library.dto.response.checkout.CheckoutBookListResponse;
import spring.library.dto.response.checkout.CheckoutBookResponse;
import spring.library.dto.response.checkout.CheckoutHistoryListResponse;
import spring.library.dto.response.checkout.CheckoutHistoryResponse;
import spring.library.exception.BookNotFoundException;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.BookRepository;
import spring.library.repository.CheckoutRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    public void checkoutBook(Long bookId, CheckoutRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException());
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException());
        Checkout checkout = Checkout.from(book, member);
        checkoutRepository.save(checkout);
    }
//    public void checkoutBook(Long bookId, CheckoutRequest request) {
//        Member member = memberRepository.findById(request.getMemberId())
//                .orElseThrow(() -> new MemberNotFoundException());
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new BookNotFoundException());
//        LocalDate dueDate;
//        String feature = String.valueOf(member.getFeature());
//
//        if (feature.equals("학생")) {
//            dueDate = LocalDate.now().plusDays(10);
//        } else if (feature.equals("교직원")) {
//            dueDate = LocalDate.now().plusDays(30);
//        } else {
//            dueDate = LocalDate.now().plusDays(110813);
//        }
//
//        Checkout checkout = Checkout.builder()
//                .book(book)
//                .member(member)
//                .loanDate(String.valueOf(LocalDate.now()))
//                .dueDate(String.valueOf(dueDate))
//                .isReturned(false)
//                .renewalCount(0)
//                .build();
//        checkoutRepository.save(checkout);
//    }

    public CheckoutBookListResponse getCheckoutList(Long memberId) {
        List<Checkout> checkouts = checkoutRepository.findByMember_MemberId(memberId);
        List<CheckoutBookResponse> bookResponses = new ArrayList<>();

        for (Checkout checkout : checkouts) {
            // book이 null이거나 member가 null이거나 반납된 책이면 continue
            if (checkout.getBook() == null || checkout.getMember() == null || checkout.isReturned()) {
                continue;
            }
            Book book = checkout.getBook();
            CheckoutBookResponse bookResponse = new CheckoutBookResponse(
                    checkout.getCheckoutId(),
                    book.getTitle(),
                    book.getAuthor(),
                    checkout.getLoanDate(),
                    checkout.getDueDate(),
                    checkout.getRenewalCount()
            );
            bookResponses.add(bookResponse);
        }
        return new CheckoutBookListResponse(bookResponses);
    }

    public CheckoutHistoryListResponse getHistoryList(Long memberId) {
        List<Checkout> checkouts = checkoutRepository.findByMember_MemberId(memberId);
        List<CheckoutHistoryResponse> bookResponses = new ArrayList<>();

        for (Checkout checkout : checkouts) {
            if (checkout.getBook() == null || checkout.getMember() == null) {
                continue;
            }
            Book book = checkout.getBook();
            CheckoutHistoryResponse bookResponse = new CheckoutHistoryResponse(
                    checkout.getCheckoutId(),
                    book.getTitle(),
                    book.getAuthor(),
                    checkout.getLoanDate(),
                    checkout.getDueDate(),
                    checkout.getRenewalCount(),
                    checkout.isReturned()
            );
            bookResponses.add(bookResponse);
        }
        return new CheckoutHistoryListResponse(bookResponses);
    }

    public void returnBook(Long checkOutId, CheckoutRequest request) {
        Checkout checkout = checkoutRepository.findByCheckoutIdAndMember_MemberId(checkOutId, request.getMemberId());
        if(checkout.isReturned()){
            throw new RuntimeException("이미 반납된 책입니다.");
        }
        checkout.updateIsReturned(true);
        checkoutRepository.save(checkout);
    }

    public void renewalBook(Long checkOutId, CheckoutRequest request) {
        Checkout checkout = checkoutRepository.findByCheckoutIdAndMember_MemberId(checkOutId, request.getMemberId());
        if(checkout.getRenewalCount() >= 1) {
            throw new RuntimeException("연장 횟수 초과");
        }
        if(checkout.getLoanDate() == LocalDate.now().toString()) {
            throw new RuntimeException("반납 예정일 당일에만 연장 가능");
        }
        if(checkout.isReturned()){
            throw new RuntimeException("반납된 책은 연장 불가");
        }
        checkout.updateRenewalCount(checkout.getRenewalCount() + 1);
        checkoutRepository.save(checkout);
    }
}
