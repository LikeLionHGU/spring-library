package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.PurchaseRequestPatchRequest;
import spring.library.controller.request.PurchaseRequestRequest;
import spring.library.controller.response.PurchaseRequestListResponse;
import spring.library.dto.PurchaseRequestDto;
import spring.library.service.PurchaseRequestService;

@RequiredArgsConstructor
@RestController
public class PurchaseRequestController {

    private final PurchaseRequestService purchaseRequestService;

    @PostMapping("/purchase-requests")
    public ResponseEntity<Object> registerPurchaseRequest(@RequestBody PurchaseRequestRequest purchaseRequestRequest){
        purchaseRequestService.registerPurchaseRequest(PurchaseRequestDto.from(purchaseRequestRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/purchase-requests")
    public ResponseEntity<PurchaseRequestListResponse> getPurchaseRequests(@RequestParam Long memberId){
        return ResponseEntity.ok(new PurchaseRequestListResponse(purchaseRequestService.getPurchaseRequests(memberId)));
    }

    @PatchMapping("/purchase-requests/{purchaseRequestId}")
    public ResponseEntity<Void> updatePurchaseRequest(@PathVariable Long purchaseRequestId, @RequestBody PurchaseRequestPatchRequest purchaseRequestPatchRequest){
        purchaseRequestService.updatePurchaseRequest(purchaseRequestId,PurchaseRequestDto.from(purchaseRequestPatchRequest));
        return ResponseEntity.ok().build();
    }
}
