package spring.library.dto;

import jakarta.persistence.Column;
import spring.library.controller.form.BookSignUpForm;
import spring.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private boolean status;
    private String publisher;
    private int publicationYear;
    private String classification;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .status(book.isStatus())
                .build();
    }

    public static BookDto from(BookSignUpForm signUpForm) { // 책 등록
        return BookDto.builder()
                .title(signUpForm.getTitle())
                .author(signUpForm.getAuthor())
                .publisher(signUpForm.getPublisher())
                .publicationYear(signUpForm.getPublicationYear())
                .classification(signUpForm.getClassification())
                .build();

    }


}
