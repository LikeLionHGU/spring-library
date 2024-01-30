package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.request.CheckoutRequest;
import spring.library.dto.response.book.CheckoutBookListResponse;
import spring.library.dto.response.book.CheckoutHistoryListResponse;
import spring.library.dto.response.member.MemberListResponse;
import spring.library.service.CheckoutService;

import java.util.List;

@RestController
@RequestMapping("/checkouts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/{bookId}")
    public void checkoutBook(@PathVariable Long bookId, @RequestBody CheckoutRequest request){
        checkoutService.checkoutBook(bookId, request);
    }

    @GetMapping
    public ResponseEntity<CheckoutBookListResponse> getCheckoutList(@RequestParam Long memberId) {
        CheckoutBookListResponse books = checkoutService.getCheckoutList(memberId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/history")
    public ResponseEntity<CheckoutHistoryListResponse> getHistoryList(@RequestParam Long memberId) {
        CheckoutHistoryListResponse books = checkoutService.getHistoryList(memberId);
        return ResponseEntity.ok(books);
    }

    @PatchMapping("/{checkOutId}/return")
    public void returnBook(@PathVariable Long checkOutId, @RequestBody CheckoutRequest request) {
        checkoutService.returnBook(checkOutId, request);
    }

    @PatchMapping("/{checkOutId}/renewal")
    public void renewalBook(@PathVariable Long checkOutId, @RequestBody CheckoutRequest request) {
        checkoutService.renewalBook(checkOutId, request);
    }

}
