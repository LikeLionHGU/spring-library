package spring.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.RentalManagement;
import spring.library.dto.MemberDto;
import spring.library.service.BookRentalService;

import java.util.ArrayList;
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

	@GetMapping("/checkouts")
	public ResponseEntity<List<RentalManagement>> showRentalBookList(@RequestParam Long memberId){
		List<RentalManagement>RentalBookList = bookRentalService.showRentalBookList(memberId);
		return ResponseEntity.ok().body(RentalBookList);
	}

	@GetMapping("/checkouts/history")
	public ResponseEntity<List<RentalManagement>> showRentalBookHistory(@RequestParam Long memberId) {
		List<RentalManagement> RentalBookHistory = bookRentalService.showRentalBookHistory(memberId);
		return ResponseEntity.ok().body(RentalBookHistory);
	}


	@PatchMapping("/checkouts/{checkOutId}/return")
	public ResponseEntity<RentalManagement> returnBook(@PathVariable Long checkOutId){
		RentalManagement rentalManagement = bookRentalService.returnBookToLibrary(checkOutId);
		return ResponseEntity.ok().body(rentalManagement);
	}

//	/checkouts/{checkOutId}/return
}
