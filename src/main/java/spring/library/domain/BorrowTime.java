package spring.library.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.sound.sampled.AudioFileFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AudioFileFormat.class)
public class BorrowTime {

  @CreatedDate private LocalDate borrowDate;
  @LastModifiedDate private LocalDate extendDue;
}
