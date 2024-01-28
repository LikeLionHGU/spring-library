package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.BookForm;
import spring.library.controller.form.MemberForm;
import spring.library.dto.request.BookRequest;
import spring.library.dto.request.MemberRequest;
import spring.library.dto.response.ApiResponse;
import spring.library.dto.response.book.BookIdResponse;
import spring.library.dto.response.book.BookListResponse;
import spring.library.dto.response.member.MemberIdResponse;
import spring.library.dto.response.member.MemberListResponse;
import spring.library.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse> createBook(@RequestBody BookForm form){
        Long bookId = bookService.createBook(BookRequest.from(form));
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<BookListResponse> getBookList() {
        BookListResponse bookListResponse = bookService.getBookList();
        return ResponseEntity.ok(bookListResponse);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long bookId, @RequestBody BookForm form){
        bookService.updateBook(bookId, BookRequest.from(form));
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }
}
