package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.PurchaseRequestRequest;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PurchaseRequestDto {
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;

    public static PurchaseRequestDto from(PurchaseRequestRequest purchaseRequestRequest){
        return PurchaseRequestDto.builder()
                .memberId(purchaseRequestRequest.getMemberId())
                .title(purchaseRequestRequest.getTitle())
                .author(purchaseRequestRequest.getAuthor())
                .publisher(purchaseRequestRequest.getPublisher())
                .publicationYear(purchaseRequestRequest.getPublicationYear())
                .build();
    }
}
