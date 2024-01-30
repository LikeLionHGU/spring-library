package spring.library.dto.response.checkout;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CheckoutBookListResponse {
    List<CheckoutBookResponse> books;

    public CheckoutBookListResponse(List<CheckoutBookResponse> books){
        this.books = books;
    }
}
