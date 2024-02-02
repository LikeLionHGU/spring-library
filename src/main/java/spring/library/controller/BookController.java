package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.BookRequest;
import spring.library.controller.response.ApiResponse;
import spring.library.controller.response.book.BookIdResponse;
import spring.library.controller.response.book.BookListResponse;
import spring.library.dto.BookDto;
import spring.library.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse> createBook(@RequestBody BookRequest bookRequest){
        Long bookId = bookService.createBook(BookDto.from(bookRequest));
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<BookListResponse> getBookList() {
        BookListResponse bookListResponse = bookService.getBookList();
        return ResponseEntity.ok(bookListResponse);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long bookId, @RequestBody BookRequest request){
        bookService.updateBook(bookId, BookDto.from(request));
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
