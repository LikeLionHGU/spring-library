package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.PurchaseRequest;
import spring.library.controller.request.PurchaseUpdateRequest;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PurchaseDto {
    private Long purchaseId;
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String processResult;
    private String requestDate;
    private String dateOfProcess;

    public static PurchaseDto from(PurchaseRequest purchaseRequest) {
        return PurchaseDto.builder()
                .memberId(purchaseRequest.getMemberId())
                .title(purchaseRequest.getTitle())
                .author(purchaseRequest.getAuthor())
                .publisher(purchaseRequest.getPublisher())
                .publicationYear(purchaseRequest.getPublicationYear())
                .build();
    }

    public static PurchaseDto from(Long memberId){
        return PurchaseDto.builder()
                .memberId(memberId)
                .build();
    }

    public static PurchaseDto from(PurchaseUpdateRequest purchaseUpdateRequest){
        return PurchaseDto.builder()
                .dateOfProcess(purchaseUpdateRequest.getDateOfProcess())
                .processResult(purchaseUpdateRequest.getProcessResult())
                .build();
    }
}