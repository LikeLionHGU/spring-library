package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.library.controller.request.CheckoutRequest;
import spring.library.sevice.CheckoutService;

@RestController
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;
    @PostMapping("/checkouts/{bookId}")
    public ResponseEntity<Void> checkoutBook(@PathVariable Long bookId, @RequestBody CheckoutRequest checkoutRequest){
        checkoutService.checkoutBook(bookId, checkoutRequest.getMemberId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
