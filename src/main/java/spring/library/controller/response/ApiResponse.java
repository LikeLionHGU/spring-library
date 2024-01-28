package spring.library.controller.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Book;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse {

    private Boolean isSuccessful = true;

    public ApiResponse(boolean isSuccessful){
        this.isSuccessful = isSuccessful;
    }

}
