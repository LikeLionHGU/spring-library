package spring.library.dto.response.purchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Purchase;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PurchaseListResponse {
    List<PurchaseResponse> purchases;

    public PurchaseListResponse(List<PurchaseResponse> purchases){
        this.purchases = purchases;
    }
}
