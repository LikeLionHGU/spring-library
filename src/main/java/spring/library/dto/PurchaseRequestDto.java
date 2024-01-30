package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.PurchaseRequestRequest;
import spring.library.domain.PurchaseRequest;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PurchaseRequestDto {
    private Long purchaseId;
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private LocalDate requestDate;
    private LocalDate dateOfProcess;
    private String processResult;

    public static PurchaseRequestDto from(PurchaseRequestRequest purchaseRequestRequest){
        return PurchaseRequestDto.builder()
                .memberId(purchaseRequestRequest.getMemberId())
                .title(purchaseRequestRequest.getTitle())
                .author(purchaseRequestRequest.getAuthor())
                .publisher(purchaseRequestRequest.getPublisher())
                .publicationYear(purchaseRequestRequest.getPublicationYear())
                .build();
    }

    public static PurchaseRequestDto from(PurchaseRequest purchaseRequest){
        return PurchaseRequestDto.builder()
                .purchaseId(purchaseRequest.getPurchaseRequestId())
                .memberId(purchaseRequest.getMember().getMemberId())
                .title(purchaseRequest.getTitle())
                .author(purchaseRequest.getAuthor())
                .publisher(purchaseRequest.getPublisher())
                .publicationYear(purchaseRequest.getPublicationYear())
                .requestDate(purchaseRequest.getRequestDate())
                .dateOfProcess(purchaseRequest.getDateOfProcess())
                .processResult(purchaseRequest.getProcessResult().getProcess())
                .build();
    }
}
