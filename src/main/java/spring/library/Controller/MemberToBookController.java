package spring.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.service.MemberToBookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/books")
public class MemberToBookController {

	private final MemberToBookService masterToBookService;

	@PostMapping
	public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
		Book response = masterToBookService.save(bookDto);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping
	public ResponseEntity<List<Book>> findAllBooks(){
		List<Book> books = masterToBookService.findAll();
		return ResponseEntity.ok().body(books);
	}

	@PatchMapping("/{bookId}")
	public ResponseEntity<Book> updateBook
		(@PathVariable Long bookId, @RequestBody BookDto updateBookDto){
		Book response = masterToBookService.update(bookId, updateBookDto);
		return ResponseEntity.ok().body(response);
	}


	@DeleteMapping("/{bookId}")
	public void deleteBook(@PathVariable Long bookId){
		masterToBookService.delete(bookId);
	}

}
