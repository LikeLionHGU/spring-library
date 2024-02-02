package spring.library.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Book;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BookListResponse{
    private List<Book> books;
    public BookListResponse(List<Book> books){
        this.books = books;
    }
}

