package spring.library.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.BookDto;
import spring.library.dto.MemberDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class HistoryListResponse extends ApiResponse {

    private List<BookDto> bookDto;


    public HistoryListResponse(List<BookDto> bookDto){
        this.bookDto = bookDto;
    }
}
