package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.CheckoutRequest;
import spring.library.controller.response.checkout.CheckoutBookListResponse;
import spring.library.controller.response.checkout.CheckoutHistoryListResponse;
import spring.library.dto.CheckoutDto;
import spring.library.service.CheckoutService;

@RestController
@RequestMapping("/checkouts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/{bookId}")
    public void checkoutBook(@PathVariable Long bookId, @RequestBody CheckoutRequest checkoutRequest){
        checkoutService.checkoutBook(bookId, CheckoutDto.from(checkoutRequest));
//        checkoutService.checkoutBook(bookId, request);
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
    public void returnBook(@PathVariable Long checkOutId, @RequestBody CheckoutRequest checkoutRequest) {
        checkoutService.returnBook(checkOutId, CheckoutDto.from(checkoutRequest));
    }

    @PatchMapping("/{checkOutId}/renewal")
    public void renewalBook(@PathVariable Long checkOutId, @RequestBody CheckoutRequest checkoutRequest) {
        checkoutService.renewalBook(checkOutId, CheckoutDto.from(checkoutRequest));
    }

}
