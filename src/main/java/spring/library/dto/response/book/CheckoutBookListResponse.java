package spring.library.dto.response.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CheckoutBookListResponse {
    List<CheckoutBookResponse> books;

    public CheckoutBookListResponse(List<CheckoutBookResponse> books){
        this.books = books;
    }
}
