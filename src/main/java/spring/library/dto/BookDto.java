package spring.library.dto;

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
public class BookDto {

  private Long bookId;

  private String title;

  private String content;
  private String author;
  private String publisher;
  private int publicationYear;
  private String classification;
  private String status;
  private int amount;

  public static BookDto from(BookForm bookForm) {
    return BookDto.builder()
        .bookId(bookForm.getBookId())
        .title(bookForm.getTitle())
        .author(bookForm.getAuthor())
        .publisher(bookForm.getPublisher())
        .publicationYear(bookForm.getPublicationYear())
        .classification(bookForm.getClassification())
        .status(bookForm.getStatus())
        .amount(bookForm.getAmount())
        .build();
  }

    public static BookDto from(Book book) {
        return BookDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification())
                .status(book.getStatus())
                .amount(book.getAmount())
                .build();
    }

}
