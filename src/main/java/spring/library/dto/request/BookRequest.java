package spring.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.form.BookForm;
import spring.library.domain.Book;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;

    public static BookRequest from(Book book) {
        return BookRequest.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification())
                .build();
    }

    public static BookRequest from(BookForm bookForm) {
        boolean status = "대출가능".equals(bookForm.getStatus());
        // 대출가능 -> true / 대출중 -> false
        return BookRequest.builder()
                .title(bookForm.getTitle())
                .author(bookForm.getAuthor())
                .publisher(bookForm.getPublisher())
                .publicationYear(bookForm.getPublicationYear())
                .classification(bookForm.getClassification())
//                .status(status)
//                .amount(bookForm.getAmount())
                .build();
    }

}
