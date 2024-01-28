package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.library.controller.request.BookRegisterRequest;
import spring.library.controller.response.BookListResponse;
import spring.library.dto.BookDto;
import spring.library.sevice.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Void> registerBook(@RequestBody BookRegisterRequest bookRegisterRequest){
        bookService.registerBook(BookDto.from(bookRegisterRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/books")
    public ResponseEntity<BookListResponse> getBooks(){
        return ResponseEntity.ok(new BookListResponse(bookService.getBooks()));
    }
}
