package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.PurchaseRequest;
import spring.library.controller.request.PurchaseUpdateRequest;
import spring.library.controller.response.ApiResponse;
import spring.library.controller.response.purchase.PurchaseIdResponse;
import spring.library.controller.response.purchase.PurchaseListResponse;
import spring.library.dto.PurchaseDto;
import spring.library.service.PurchaseService;

@RestController
@RequestMapping("/purchase-requests")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<ApiResponse> createPurchase(@RequestBody PurchaseRequest purchaseRequest){
        Long purchaseId = purchaseService.createPurchase(PurchaseDto.from(purchaseRequest));
        ApiResponse response = new PurchaseIdResponse(purchaseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PurchaseListResponse> getPurchaseList(@RequestParam Long memberId) {
        PurchaseListResponse response = purchaseService.getPurchaseList(PurchaseDto.from(memberId));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{purchaseId}")
    public void updatePurchase(@PathVariable Long purchaseId, @RequestBody PurchaseUpdateRequest purchaseUpdateRequest) {
        PurchaseDto purchaseDto = PurchaseDto.from(purchaseUpdateRequest);
        purchaseService.updatePurchase(purchaseId, purchaseDto);
    }
}
