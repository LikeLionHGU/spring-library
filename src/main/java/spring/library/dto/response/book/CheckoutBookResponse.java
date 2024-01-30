package spring.library.dto.response.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CheckoutBookResponse {
    private Long checkoutId;
    private String title;
    private String author;
    private String checkOutDate;
    private String dueDate;
    private int renewalCount;

    public CheckoutBookResponse(Long checkoutId, String title, String author, String checkOutDate, String dueDate, int renewalCount){
        this.checkoutId = checkoutId;
        this.title = title;
        this.author = author;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.renewalCount = renewalCount;
    }
}
