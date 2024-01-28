package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.BookRegisterRequest;

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
}
