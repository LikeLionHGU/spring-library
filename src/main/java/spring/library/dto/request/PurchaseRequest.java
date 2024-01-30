package spring.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.form.PurchaseForm;
import spring.library.domain.ProcessResult;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String requestDate;
    private String dateOfProcess;
    private ProcessResult processResult;

    public static PurchaseRequest from(PurchaseForm purchaseForm) {
        return PurchaseRequest.builder()
                .memberId(purchaseForm.getMemberId())
                .title(purchaseForm.getTitle())
                .author(purchaseForm.getAuthor())
                .publisher(purchaseForm.getPublisher())
                .publicationYear(purchaseForm.getPublicationYear())
                .build();
    }
    public static PurchaseRequest from(String dateOfProcess, ProcessResult processResult) {
        return PurchaseRequest.builder()
                .dateOfProcess(dateOfProcess)
                .processResult(processResult)
                .build();
    }
}
