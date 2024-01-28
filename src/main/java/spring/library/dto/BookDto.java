package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.BookRegisterRequest;
import spring.library.domain.Book;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    private int amount;

    public static BookDto from(BookRegisterRequest bookRegisterRequest){
        return BookDto.builder()
                .title(bookRegisterRequest.getTitle())
                .author(bookRegisterRequest.getAuthor())
                .publisher(bookRegisterRequest.getPublisher())
                .publicationYear(bookRegisterRequest.getPublicationYear())
                .classification(bookRegisterRequest.getClassification())
                .status(bookRegisterRequest.getStatus())
                .amount(bookRegisterRequest.getAmount())
                .build();
    }

    public static BookDto from(Book book){
        return BookDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification().getClassification())
                .status(book.getStatus().getStatus())
                .amount(book.getAmount())
                .build();
    }
}
