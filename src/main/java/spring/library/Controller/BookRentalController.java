package spring.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.library.service.BookRentalService;

@RequiredArgsConstructor
@RestController
public class BookRentalController {

	private final BookRentalService bookRentalService;

	@PostMapping("/checkout/{bookId}")
	public void rentBook(@PathVariable Long bookId, @RequestBody Long memberId){
		bookRentalService.rentBook(bookId, memberId);
	}



}
