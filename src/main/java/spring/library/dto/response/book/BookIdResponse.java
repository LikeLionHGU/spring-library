package spring.library.dto.response.book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.response.ApiResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookIdResponse extends ApiResponse {
    private Long bookId;
}
