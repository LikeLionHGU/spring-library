package spring.library.controller.response.purchase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.response.ApiResponse;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PurchaseIdResponse extends ApiResponse {
    private Long wishBookId;

    public PurchaseIdResponse(Long wishBookId) {
        this.wishBookId = wishBookId;
    }
}
