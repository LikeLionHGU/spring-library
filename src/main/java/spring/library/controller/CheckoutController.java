package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.response.ApiResponse;
import spring.library.controller.response.BookListResponse;
import spring.library.dto.BookDto;
import spring.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
@CrossOrigin
public class CheckoutController {

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getMemberBook(@PathVariable Long memberId){
        List<BookDto> bookDto = bookService.bookBorrowed(memberId);
        ApiResponse response =new BookListResponse(bookDto);
        return ResponseEntity.ok(response);

    }




}
