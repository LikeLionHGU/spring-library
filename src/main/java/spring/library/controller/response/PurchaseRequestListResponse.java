package spring.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.PurchaseRequestDto;

import java.time.LocalDate;
import java.util.List;

@Getter
public class PurchaseRequestListResponse {
    private final List<PurchaseRequest> purchaseRequests;

    public PurchaseRequestListResponse(List<PurchaseRequestDto> purchaseRequestDtos){
        this.purchaseRequests = purchaseRequestDtos.stream()
                .map(PurchaseRequest::from)
                .toList();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class PurchaseRequest{
        private Long purchaseId;
        private String title;
        private String author;
        private LocalDate requestDate;
        private LocalDate dateOfProcess;
        private String processResult;

        public static PurchaseRequest from(PurchaseRequestDto purchaseRequestDto){
            return PurchaseRequest.builder()
                    .purchaseId(purchaseRequestDto.getPurchaseId())
                    .title(purchaseRequestDto.getTitle())
                    .author(purchaseRequestDto.getAuthor())
                    .requestDate(purchaseRequestDto.getRequestDate())
                    .dateOfProcess(purchaseRequestDto.getDateOfProcess())
                    .processResult(purchaseRequestDto.getProcessResult())
                    .build();
        }
    }
}
