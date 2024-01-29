package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.enums.BookClassification;
import spring.library.domain.enums.BookStatus;
import spring.library.dto.BookDto;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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

    @Column(name = "amount", nullable = false)
    private int amount;

    public static Book from(BookDto bookDto){
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .publicationYear(bookDto.getPublicationYear())
                .classification(BookClassification.from(bookDto.getClassification()))
                .status(BookStatus.AVAILABLE)
                .amount(bookDto.getAmount())
                .build();
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void controlStatus(int size) {
        if (size >= amount) {
            this.status = BookStatus.UNAVAILABLE;
        } else {
            this.status = BookStatus.AVAILABLE;
        }
    }
}
