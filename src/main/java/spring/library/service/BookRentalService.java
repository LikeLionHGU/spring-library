package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.domain.RentalManagement;
import spring.library.repository.BookHistoryRepository;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
@RequiredArgsConstructor
public class BookRentalService {

	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final BookHistoryRepository bookHistoryRepository;

	public int maxBorrowCount;
	public int borrowRangeDay;

	public RentalManagement rentBook(Long bookId, Long memberId) throws IllegalArgumentException {
		Book rentedBook = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("대출할 도서를 찾지 못했습니다! rentalService 확인해주세요."));
		Member renter = memberRepository.findById(memberId).orElseThrow( () -> new IllegalArgumentException("대출한 대상에 대해서 찾지 못했습니다! rentalService 확인해주세요"));
		String renterFeature = renter.getFeature(); // 교직원, 학생, master구분

		// todo : enum 처리 가능하면, enum으로
		switch (renterFeature) {
			case "학생" -> {
				maxBorrowCount = 10;
				borrowRangeDay = 10;
			}
			case "교직원" -> {
				maxBorrowCount = 20;
				borrowRangeDay = 30;
			}
			case "관리자" -> {
				maxBorrowCount = 100;
				borrowRangeDay = 110813;
			}
			default -> throw new IllegalArgumentException("허락되지 않은 feature입니다. 회원 등록부터 다시 해주세요.");
		}

		// todo : 여기에서 예외 처리 해주기
		// todo : 대출 도서가 maxBorrowCount 보다 큰지 확인 필요. -> 예외처리 필수.
		// todo : 대출된 것 빌리려고 하는 경우, 예외처리
		if (maxBorrowCount <= renter.getRentalHistory().size())
			throw new IllegalArgumentException("대여할 수 있는 범위를 초과했습니다."); // 작동안함. repo에서 읽어와서 해야할 듯 함.

		RentalManagement rentalManagement = RentalManagement.RentBookToRentalManagement(rentedBook, borrowRangeDay, memberId);

		renter.getRentalHistory().add(rentalManagement);    // 작동 안함. -> 휘발됨.
		memberRepository.save(renter); // OneToMany의 cascade = CascadeType.ALL 때문에 넣은 것!

		RentalManagement savedRentalManagement = bookHistoryRepository.save(rentalManagement);

		return savedRentalManagement;
	}

	public List<RentalManagement> showRentalBookList(Long memberId){
		if (!memberRepository.existsById(memberId)) { throw new IllegalArgumentException("해당 멤버의 기록이 없습니다."); }
		List<RentalManagement> firstCheckBooks = bookHistoryRepository.findByRentMemberId(memberId);
		List<RentalManagement> rentedBooks = new ArrayList<>(); ;
		for(RentalManagement book : firstCheckBooks){
			boolean isRental = book.getStatus().equals("대출 중");
			if(isRental) rentedBooks.add(book);
		}
		return rentedBooks;
	}

	public List<RentalManagement> showRentalBookHistory(Long memberId) {
		if (!memberRepository.existsById(memberId)) { throw new IllegalArgumentException("해당 멤버의 기록이 없습니다."); }
		List<RentalManagement> rentedBooks = bookHistoryRepository.findByRentMemberId(memberId);
		return rentedBooks;
	}

	public RentalManagement returnBookToLibrary(Long bookId){
		Book rentedBook = bookRepository.findById(bookId).orElseThrow( () -> new IllegalArgumentException("대출한 도서를 찾지 못했습니다! returnBookToLibrary 확인해주세요."));
		RentalManagement rentalManagement = RentalManagement.ReturnBookToRentalManagerment(rentedBook);
		RentalManagement savedRentalManagement = bookHistoryRepository.save(rentalManagement);
		return savedRentalManagement;

	}

}