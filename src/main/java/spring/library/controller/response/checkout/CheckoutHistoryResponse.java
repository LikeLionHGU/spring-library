package spring.library.controller.response.checkout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Checkout;
import spring.library.dto.CheckoutDto;

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

//    public CheckoutHistoryResponse(Long checkoutId, String title, String author, String checkOutDate, String dueDate, int renewalCount, boolean isReturned){
//        this.checkoutId = checkoutId;
//        this.title = title;
//        this.author = author;
//        this.checkOutDate = checkOutDate;
//        this.dueDate = dueDate;
//        this.renewalCount = renewalCount;
//        this.isReturned = isReturned;
//    }
    public CheckoutHistoryResponse(Checkout checkout){
        this.checkoutId = checkout.getCheckoutId();
        this.title = checkout.getBook().getTitle();
        this.author = checkout.getBook().getAuthor();
        this.checkOutDate = checkout.getCheckOutDate();
        this.dueDate = checkout.getDueDate();
        this.renewalCount = checkout.getRenewalCount();
        this.isReturned = checkout.isReturned();
    }
    public CheckoutHistoryResponse(CheckoutDto checkoutDto){
        this.checkoutId = checkoutDto.getCheckOutId();
        this.title = checkoutDto.getTitle();
        this.author = checkoutDto.getAuthor();
        this.checkOutDate = checkoutDto.getCheckOutDate();
        this.dueDate = checkoutDto.getDueDate();
        this.renewalCount = checkoutDto.getRenewalCount();
        this.isReturned = checkoutDto.isReturned();
    }
}
