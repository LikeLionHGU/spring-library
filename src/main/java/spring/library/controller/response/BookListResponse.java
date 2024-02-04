package spring.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.BookDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BookListResponse extends ApiResponse {

    private List<BookDto> books;

    public BookListResponse(List<BookDto> bookDto){
        this.books = bookDto;
    }

}
