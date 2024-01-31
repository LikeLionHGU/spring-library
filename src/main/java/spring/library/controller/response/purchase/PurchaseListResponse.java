package spring.library.controller.response.purchase;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PurchaseListResponse {
    List<PurchaseResponse> purchases;

    public PurchaseListResponse(List<PurchaseResponse> purchases){
        this.purchases = purchases;
    }
}
