package spring.library.dto.response.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CheckoutHistoryListResponse {
    List<CheckoutHistoryResponse> books;

    public CheckoutHistoryListResponse(List<CheckoutHistoryResponse> books){
        this.books = books;
    }
}
