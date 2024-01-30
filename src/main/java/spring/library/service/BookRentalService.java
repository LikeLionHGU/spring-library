package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor // final, nullable인 것만 생성자로 만듦
public class BookRentalService {

	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;

	// 책 대여가 가능한지 확인한 다음, 대여 가능하면, 해당 memberId에다가 ArrayList만들어서 넣어주기
	// 불가하면, 대여가 불가하다 띄워주기

	public int maxBorrowCount;
	public int borrowRangeday;

	public void rentBook(Long bookId, Long memberId){
        Book rentedBook = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("대출할 도서를 찾지 못했습니다! rentalService 확인해주세요."));
		Member renter = memberRepository.findById(memberId).orElseThrow(()-> new IllegalArgumentException("대출한 대상에 대해서 찾지 못했습니다! rentalService 확인해주세요"));
		String renterFeature = renter.getFeature();
		// todo : enum 처리 가능하면, enum으로
		switch (renterFeature){
			case "학생" -> {
				maxBorrowCount = 10;
				borrowRangeday=10;
			}
			case "교직원" -> {
				maxBorrowCount = 20;
				borrowRangeday = 30;
			}
			case "관리자" -> {
				maxBorrowCount = 100;
				borrowRangeday = 110813;
			}
		}

//		 date를 입력해주는 것이 아니라, 해준 시기를 기점으로 해야함.


	}


	// 책 연장이 가능한지 확인한 다음, 마감일에서 + 5 해주기.

}
