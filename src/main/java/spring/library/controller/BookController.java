package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.BookCheckoutRequest;
import spring.library.controller.request.BookRegisterRequest;
import spring.library.controller.response.BookListResponse;
import spring.library.dto.BookDto;
import spring.library.sevice.BookService;
import spring.library.sevice.CheckoutListService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CheckoutListService checkoutListService;

    @PostMapping("/books")
    public ResponseEntity<Void> registerBook(@RequestBody BookRegisterRequest bookRegisterRequest){
        bookService.registerBook(BookDto.from(bookRegisterRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/books")
    public ResponseEntity<BookListResponse> getBooks(){
        return ResponseEntity.ok(new BookListResponse(bookService.getBooks()));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable Long bookId, @RequestBody BookRegisterRequest bookRegisterRequest){
        bookService.updateBook(bookId, BookDto.from(bookRegisterRequest));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/books/{bookId}/checkout")
    public ResponseEntity<Void> checkoutBook(@PathVariable Long bookId, @RequestBody BookCheckoutRequest bookCheckoutRequest){
        checkoutListService.checkoutBook(bookId, bookCheckoutRequest.getMemberId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
