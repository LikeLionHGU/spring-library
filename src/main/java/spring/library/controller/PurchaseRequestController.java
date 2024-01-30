package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.library.controller.request.PurchaseRequestRequest;
import spring.library.dto.PurchaseRequestDto;
import spring.library.sevice.PurchaseRequestService;

@RequiredArgsConstructor
@RestController
public class PurchaseRequestController {

    private final PurchaseRequestService purchaseRequestService;

    @PostMapping("/purchase-request")
    public ResponseEntity<Object> registerPurchaseRequest(PurchaseRequestRequest purchaseRequestRequest){
        purchaseRequestService.registerPurchaseRequest(PurchaseRequestDto.from(purchaseRequestRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
