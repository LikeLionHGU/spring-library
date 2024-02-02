package spring.library.controller.form;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSignUpForm {
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
}
