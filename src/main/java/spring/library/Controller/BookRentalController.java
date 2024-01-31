package spring.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.RentalManagement;
import spring.library.dto.MemberDto;
import spring.library.service.BookRentalService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRentalController {

	private final BookRentalService bookRentalService;

	@PostMapping("/checkouts/{bookId}")
	public ResponseEntity<RentalManagement> rentBook(
		@PathVariable Long bookId, @RequestBody MemberDto memberDto) throws IllegalArgumentException {
		RentalManagement rentalManagement = bookRentalService.rentBook(bookId, memberDto.getMemberId());
		return ResponseEntity.ok().body(rentalManagement);
	}

	@GetMapping("/checkouts/history")
//	public ResponseEntity<List<Book>> showRentalBookHistory(@RequestParam Long memberId) {
	public ResponseEntity<List<RentalManagement>> showRentalBookHistory(@RequestParam Long memberId) {
		List<RentalManagement> RentalBookHistory = bookRentalService.showRentalBookHistory(memberId);
//		List<Book> RentalBookHistory = bookRentalService.showRentalBookHistory(memberId);
		return ResponseEntity.ok().body(RentalBookHistory);
	}

}
