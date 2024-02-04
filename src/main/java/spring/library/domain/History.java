package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import spring.library.dto.BookDto;
import spring.library.dto.HistoryDto;

import javax.sound.sampled.AudioFileFormat;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AudioFileFormat.class)
public class History {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long historyId;

  private LocalDate borrowDate;
  private LocalDate due;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memberId")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bookId")
  private Book book;



  public static History toHistory(Member member, Book book) {
    return History.builder()
            .borrowDate(LocalDate.now())
            .member(member)
            .book(book)
            .build();

  }
}
