package spring.library.controller.response.purchase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.ProcessResult;
import spring.library.domain.Purchase;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PurchaseResponse {
    private Long purchaseId;
    private String title;
    private String author;
    private String requestDate;
    private String dateOfProcess;
    private String processResult;

    public PurchaseResponse(Long purchaseId, String title, String author, String requestDate, String dateOfProcess, ProcessResult processResult) {
        this.purchaseId = purchaseId;
        this.title = title;
        this.author = author;
        this.requestDate = requestDate;
        this.dateOfProcess = dateOfProcess;
        this.processResult = processResult.getProcessResult();
    }

    public PurchaseResponse(Purchase purchase) {
        this.purchaseId = purchase.getPurchaseId();
        this.title = purchase.getTitle();
        this.author = purchase.getAuthor();
        this.requestDate = purchase.getRequestDate();
        this.dateOfProcess = purchase.getDateOfProcess();
        this.processResult = purchase.getProcessResult().getProcessResult();
    }
}
