package spring.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.dto.MemberDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CheckoutListResponse extends ApiResponse {

    private List<BookDto> books;

    public CheckoutListResponse(List<BookDto> bookDto){
        this.books = bookDto;
    }


}
