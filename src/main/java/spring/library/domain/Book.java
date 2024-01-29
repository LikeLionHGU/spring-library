package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.domain.enums.BookClassification;
import spring.library.domain.enums.BookStatus;
import spring.library.dto.BookDto;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Book extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "author", length = 30, nullable = false)
    private String author;

    @Column(name = "publisher", length = 30, nullable = false)
    private String publisher;

    @Column(name = "publication_year", length = 30, nullable = false)
    private int publicationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "classification", nullable = false)
    private BookClassification classification;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

    public static Book from(BookDto bookDto){
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publicationYear(bookDto.getPublicationYear())
                .classification(BookClassification.from(bookDto.getClassification()))
                .status(BookStatus.AVAILABLE)
                .build();
    }
}
