package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.dto.BookDto;
import jakarta.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Book extends BorrowTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookId;

  private String title;
  private String author;
  private String publisher;
  private int publicationYear;
  private String classification;
  private String status;
  private int amount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberId")
  private Member member;

  public static Book toBook(BookDto bookDto) {
    return Book.builder()
        .title(bookDto.getTitle())
        .author(bookDto.getAuthor())
        .publisher(bookDto.getPublisher())
        .publicationYear(bookDto.getPublicationYear())
        .classification(bookDto.getClassification())
        .status(bookDto.getStatus())
        .amount(bookDto.getAmount())
        .build();
  }
}
