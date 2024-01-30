package spring.library.dto.response.checkout;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CheckoutHistoryResponse {
    private Long checkoutId;
    private String title;
    private String author;
    private String checkOutDate;
    private String dueDate;
    private int renewalCount;
    private boolean isReturned;

    public CheckoutHistoryResponse(Long checkoutId, String title, String author, String checkOutDate, String dueDate, int renewalCount, boolean isReturned){
        this.checkoutId = checkoutId;
        this.title = title;
        this.author = author;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.renewalCount = renewalCount;
        this.isReturned = isReturned;
    }
}
