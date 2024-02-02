package spring.library.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
}
