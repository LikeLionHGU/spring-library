package spring.library.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookForm {

  private Long bookId;
  private String title;
  private String content;
  private String author;
  private String publisher;
  private int publicationYear;
  private String classification;
  private String status;
  private int amount;
}
