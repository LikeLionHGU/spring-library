package spring.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.BookSignUpForm;
import spring.library.controller.response.ApiResponse;
import spring.library.controller.response.BookIdResponse;
import spring.library.dto.BookDto;
import spring.library.service.BookService;
import spring.library.controller.response.BookListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService bookService;
    @GetMapping
    public ResponseEntity<BookListResponse> getBookList() {
        BookListResponse bookListResponse = bookService.getAllBooks();
        return ResponseEntity.ok(bookListResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookSignUpForm bookSignUpForm){
        Long bookId = bookService.addBook(BookDto.from(bookSignUpForm));
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long bookId, @RequestBody BookSignUpForm bookSignUpForm){
        bookService.updateBook(bookId, BookDto.from(bookSignUpForm));
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }



}
