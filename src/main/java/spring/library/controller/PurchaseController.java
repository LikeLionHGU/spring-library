package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.PurchaseForm;
import spring.library.controller.form.PurchaseUpdateForm;
import spring.library.dto.request.PurchaseRequest;
import spring.library.dto.response.ApiResponse;
import spring.library.dto.response.purchase.PurchaseIdResponse;
import spring.library.dto.response.purchase.PurchaseListResponse;
import spring.library.service.PurchaseService;

@RestController
@RequestMapping("/purchase-requests")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<ApiResponse> createWishBook(@RequestBody PurchaseForm form){
        Long purchaseId = purchaseService.createPurchase(PurchaseRequest.from(form));
        ApiResponse response = new PurchaseIdResponse(purchaseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PurchaseListResponse> getWishBookList(@RequestParam Long memberId) {
        PurchaseListResponse response = purchaseService.getPurchaseList(memberId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{purchaseId}")
    public void updateWishBook(@PathVariable Long purchaseId, @RequestBody PurchaseUpdateForm form) {
        purchaseService.updatePurchase(purchaseId, form);
    }
}
