package spring.library.dto;

import lombok.*;
import spring.library.controller.form.BookForm;
import spring.library.domain.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

  private Long bookId;

  private String title;

  private String author;
  private String publisher;
  private int publicationYear;
  private String classification;
  private String status;
  private int amount;

  private LocalDate borrowDate;
  private LocalDate extendDue;

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
                .borrowDate(book.getBorrowDate())
              .build();
    }

}
