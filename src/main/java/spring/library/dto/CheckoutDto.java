package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Checkout;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CheckoutDto {
    private Long checkOutId;
    private String title;
    private String author;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private int renewalCount;
    private boolean isReturned;

    public static CheckoutDto from(Checkout checkout) {
        return CheckoutDto.builder()
                .checkOutId(checkout.getCheckoutListId())
                .title(checkout.getBook().getTitle())
                .author(checkout.getBook().getAuthor())
                .checkOutDate(checkout.getLoanDate())
                .dueDate(checkout.getDueDate())
                .renewalCount(checkout.getRenewalCount())
                .isReturned(checkout.isReturned())
                .build();
    }
}
