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

import java.util.List;

@Service
@CrossOrigin
@RequiredArgsConstructor
public class BookRentalService {

	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final BookHistoryRepository bookHistoryRepository;

	public int maxBorrowCount;
	public int borrowRangeday;

	public RentalManagement rentBook(Long bookId, Long memberId) throws IllegalArgumentException {
		Book rentedBook =
			bookRepository
				.findById(bookId)
				.orElseThrow(
					() -> new IllegalArgumentException("대출할 도서를 찾지 못했습니다! rentalService 확인해주세요."));
		Member renter =
			(memberRepository
				.findById(memberId)
				.orElseThrow(
					() -> new IllegalArgumentException("대출한 대상에 대해서 찾지 못했습니다! rentalService 확인해주세요")));
		String renterFeature = renter.getFeature(); // 교직원, 학생, master구분

		// todo : enum 처리 가능하면, enum으로
		switch (renterFeature) {
			case "학생" -> {
				maxBorrowCount = 10;
				borrowRangeday = 10;
			}
			case "교직원" -> {
				maxBorrowCount = 20;
				borrowRangeday = 30;
			}
			case "관리자" -> {
				maxBorrowCount = 100;
				borrowRangeday = 110813;
			}
			default -> throw new IllegalArgumentException("허락되지 않은 feature입니다. 회원 등록부터 다시 해주세요.");
		}

		// todo : 여기에서 예외 처리 해주기
		// todo : 대출 도서가 maxBorrowCount 보다 큰지 확인 필요. -> 예외처리 필수.
		// todo : 대출된 것 빌리려고 하는 경우, 예외처리
		if(maxBorrowCount <= renter.getRentalHistory().size()) throw new IllegalArgumentException("대여할 수 있는 범위를 초과했습니다.");  // 작동안함. repo에서 읽어와서 해야할 듯 함.


		RentalManagement rentalManagement =
			RentalManagement.BookToRentalManagement(rentedBook, borrowRangeday, memberId);

		renter.getRentalHistory().add(rentalManagement);
		memberRepository.save(renter);

		RentalManagement savedRentalManagement = bookHistoryRepository.save(rentalManagement);




		return savedRentalManagement;
	}



	// BookHistoryRepository 있는 걸 다 돌려. -> member_id랑 같은 정보들 다 가져와
	public List<RentalManagement> showRentalBookHistory(Long memberId){
		Member targetMember = memberRepository.findById(memberId).orElseThrow(() -> {throw new IllegalArgumentException("해당 멤버의 기록이 없습니다.");});

		List<RentalManagement> rentedBooks = targetMember.getRentalHistory();
		for (RentalManagement book : rentedBooks) {
			System.out.println("1123" + book);
		}
    System.out.println("12312");
		return rentedBooks;
	}

//public List<RentalManagement> showRentalBookHistory(Long memberId){
//	Member targetMember = memberRepository.findById(memberId).orElseThrow(() -> {throw new IllegalArgumentException("해당 멤버의 기록이 없습니다.");});
//	List<RentalManagement> rentedBooks = bookHistoryRepository.findAllByMember(targetMember).orElseThrow(() -> {throw new IllegalArgumentException("해당 멤버의 대출 이력이 없습니다.");});  // RentalManagement 객체를 조회
//	for (RentalManagement book : rentedBooks) {
//		System.out.println("1123" + book);
//	}
//	System.out.println("12312");
//	return rentedBooks;
//}


}



/*
package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.domain.RentalManagement;
import spring.library.repository.BookHistoryReposiory;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@CrossOrigin
@RequiredArgsConstructor
public class BookRentalService {

	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final BookHistoryReposiory bookHistoryReposiory;

	public int maxBorrowCount;
	public int borrowRangeday;

	public RentalManagement rentBook(Long bookId, Long memberId) throws IllegalArgumentException {
		Book rentedBook =
			bookRepository
				.findById(bookId)
				.orElseThrow(
					() -> new IllegalArgumentException("대출할 도서를 찾지 못했습니다! rentalService 확인해주세요."));
		Member renter =
			(memberRepository
				.findById(memberId)
				.orElseThrow(
					() -> new IllegalArgumentException("대출한 대상에 대해서 찾지 못했습니다! rentalService 확인해주세요")));
		String renterFeature = renter.getFeature();

		// todo : enum 처리 가능하면, enum으로
		switch (renterFeature) {
			case "학생" -> {
				maxBorrowCount = 10;
				borrowRangeday = 10;
			}
			case "교직원" -> {
				maxBorrowCount = 20;
				borrowRangeday = 30;
			}
			case "관리자" -> {
				maxBorrowCount = 100;
				borrowRangeday = 110813;
			}
			default -> throw new IllegalArgumentException("허락되지 않은 feature입니다. 회원 등록부터 다시 해주세요.");
		}

		RentalManagement rentalManagement =
			RentalManagement.BookToRentalManagement(rentedBook, borrowRangeday);

		renter.getRentalHistory().add(rentalManagement);
		memberRepository.save(renter);  // renter에 대한 변경을 데이터베이스에 반영

		// todo : 대출 도서가 maxBorrowCount 보다 큰지 확인 필요. -> 예외처리 필수.
		// todo : 대출된 것 빌리려고 하는 경우, 예외처리
		if(maxBorrowCount < renter.getRentalHistory().size()) throw new IllegalArgumentException("대여할 수 있는 범위를 초과했습니다.");

		return rentalManagement;
	}

//	public List<Book> showRentalBookHistory(Long memberId){
//		MemberDto targetMember = MemberDto.from(memberRepository.findById(memberId).orElseThrow(() -> {throw new IllegalArgumentException("해당 멤버의 기록이 없습니다.");}));
//		List<Book> storeBook = targetMember.getRentalHistory();
//    for (Book tmp : storeBook) System.out.println(tmp);
//		return storeBook;
//	}

	public List<RentalManagement> showRentalBookHistory(Long memberId){
		Member targetMember = memberRepository.findById(memberId).orElseThrow(() -> {throw new IllegalArgumentException("해당 멤버의 기록이 없습니다.");});
		List<RentalManagement> rentedBooks = targetMember.getRentalHistory();
		for (RentalManagement book : rentedBooks) {
			System.out.println("1123" + book);
		}
    System.out.println("12312");
		return rentedBooks;
	}


}


 */