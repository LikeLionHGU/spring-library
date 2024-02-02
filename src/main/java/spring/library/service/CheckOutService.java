package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.CheckOut;
import spring.library.domain.Member;
import spring.library.controller.form.CheckOutForm;
import spring.library.dto.CheckOutDto;
import spring.library.exception.BookNotFoundException;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.BookRepository;
import spring.library.repository.CheckOutRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckOutService {

    private final CheckOutRepository checkOutRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 도서 대출하기
//    public void checkoutBook(Long bookId, CheckOutDto checkoutDto) {
//        Member member = memberRepository.findById(checkoutDto.getMemberId()).orElseThrow(() -> new MemberNotFoundException());
//        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException());
//        if(book.isStatus().equals(true)){
//            CheckOut checkOut = CheckOut.from(book, member);
//            book.updateStatus("대출중");
//            bookRepository.save(book);
//            checkOutRepository.save(checkOut);
//        }
//        else {
//            throw new RuntimeException("대출 불가능한 책입니다.");
//        }
//
//
//    }

    // 도서 반납하기
    public void returnBook(Long checkOutId, CheckOutDto checkoutDto) {
        CheckOut checkout = checkOutRepository.findByCheckoutIdAndMember_MemberId(checkOutId, checkoutDto.getMemberId());
        Book book = checkout.getBook();
        if(checkout.isReturned()){
            throw new RuntimeException("이미 반납된 책입니다.");
        }

    }

    // 대출 중인 책 조회하기

    // 도서 대여 연장하기

}
