package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.CheckoutRequest;
import spring.library.domain.Checkout;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CheckoutDto {
    private Long checkOutId;
    private Long memberId;
    private String title;
    private String author;
    private String checkOutDate;
    private String dueDate;
    private int renewalCount;
    private boolean isReturned;

    public static CheckoutDto from(Checkout checkout){
        return CheckoutDto.builder()
                .checkOutId(checkout.getCheckoutId())
                .title(checkout.getBook().getTitle())
                .author(checkout.getBook().getAuthor())
                .checkOutDate(checkout.getCheckOutDate())
                .dueDate(checkout.getDueDate())
                .renewalCount(checkout.getRenewalCount())
                .isReturned(checkout.isReturned())
                .build();
    }

    public static CheckoutDto from (CheckoutRequest checkoutRequest) {
        return CheckoutDto.builder()
                .memberId(checkoutRequest.getMemberId())
                .build();
    }
}
