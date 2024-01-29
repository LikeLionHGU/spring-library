package spring.library.controller.request;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookRegisterRequest {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
}
