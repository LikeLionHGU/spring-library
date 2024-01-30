package spring.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.service.MemberToBookService;

import java.util.List;

@RestController
@RequiredArgsConstructor    // 일단은 final이 붙은 것만 생성자로 만듦.
@CrossOrigin    // controller에선 거진 필수!
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

//	질문하기 -> 위 아래 모두 동일한 역할을 해. 그런데, type에 Book / BookDto 이 차이 뿐인데, 무엇으로 하는 게 더 좋은 걸까?

//	@GetMapping
//	public ResponseEntity<List<BookDto>> findAllBooks(){
//		List<BookDto> books = masterToBookService.findAll()
//			.stream()
//			.map(BookDto::new)
//			.toList();
//		return ResponseEntity.ok().body(books);

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
