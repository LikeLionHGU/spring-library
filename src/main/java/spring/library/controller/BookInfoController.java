package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.BookForm;
import spring.library.controller.response.ApiResponse;
import spring.library.controller.response.BookIdResponse;
import spring.library.controller.response.BookListResponse;
import spring.library.controller.response.BookResponse;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin
public class BookInfoController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookForm form){
        Long bookId = bookService.addBook(BookDto.from(form));
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse> getBook(@PathVariable Long bookId){
        BookDto bookDto = bookService.getBook(bookId);
        ApiResponse response = new BookResponse(bookDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBooks(){
        List<BookDto> bookDto = bookService.getAllBooks();
    ApiResponse response = new BookListResponse(bookDto);
    return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        ApiResponse response = new BookIdResponse(bookId);
        return ResponseEntity.ok(response);
    }


}
