package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.form.CheckOutForm;
import spring.library.domain.CheckOut;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CheckOutDto {
    private Long checkOutId;
    private Long memberId;
    private String title;
    private String author;
    private String checkOutDate;
    private String dueDate;
    private int renewalCount;
    private boolean isReturned;

    public static CheckOutDto from(CheckOut checkout){
        return CheckOutDto.builder()
                .checkOutId(checkout.getCheckoutId())
                .title(checkout.getBook().getTitle())
                .author(checkout.getBook().getAuthor())
                .checkOutDate(checkout.getCheckOutDate())
                .dueDate(checkout.getDueDate())
                .renewalCount(checkout.getRenewalCount())
                .isReturned(checkout.isReturned())
                .build();
    }

    public static CheckOutDto from (CheckOutForm checkoutRequest) {
        return CheckOutDto.builder()
                .memberId(checkoutRequest.getMemberId())
                .build();
    }

}