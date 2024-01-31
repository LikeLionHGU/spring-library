package spring.library.dto;

import lombok.*;
import spring.library.domain.Book;
import spring.library.domain.History;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {

  private long historyId;

  private LocalDate borrowDate;
  private LocalDate due;

  public static HistoryDto from(History history) {
    return HistoryDto.builder()
            .historyId(history.getHistoryId())
            .build();
  }

}
