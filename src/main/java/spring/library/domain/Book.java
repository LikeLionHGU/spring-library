package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.domain.enums.BookClassification;
import spring.library.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "book")
    private List<Checkout> checkouts = new ArrayList<>();

    public static Book from(BookDto bookDto){
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publicationYear(bookDto.getPublicationYear())
                .classification(BookClassification.from(bookDto.getClassification()))
                .build();
    }

    public void updateBookExceptStatus(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.publisher = bookDto.getPublisher();
        this.publicationYear = bookDto.getPublicationYear();
        this.classification = BookClassification.from(bookDto.getClassification());
    }
}
