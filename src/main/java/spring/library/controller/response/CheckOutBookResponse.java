package spring.library.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.CheckOut;
import spring.library.dto.CheckOutDto;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CheckOutBookResponse {
    private Long checkoutId;
    private String title;
    private String author;
    private String checkOutDate;
    private String dueDate;
    private int renewalCount;

    public CheckOutBookResponse(CheckOut checkOut){
        this.checkoutId = checkOut.getCheckoutId();
        this.title = checkOut.getBook().getTitle();
        this.author = checkOut.getBook().getAuthor();
        this.checkOutDate = checkOut.getCheckOutDate();
        this.dueDate = checkOut.getDueDate();
        this.renewalCount = checkOut.getRenewalCount();
    }
    public CheckOutBookResponse(CheckOutDto checkOutDto){
        this.checkoutId = checkOutDto.getCheckOutId();
        this.title = checkOutDto.getTitle();
        this.author = checkOutDto.getAuthor();
        this.checkOutDate = checkOutDto.getCheckOutDate();
        this.dueDate = checkOutDto.getDueDate();
        this.renewalCount = checkOutDto.getRenewalCount();
    }
}