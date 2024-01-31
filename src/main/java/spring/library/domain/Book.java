package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import spring.library.dto.BookDto;
import jakarta.persistence.Entity;

import javax.sound.sampled.AudioFileFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Book {

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

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "borrowDate")
  private History history;




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
