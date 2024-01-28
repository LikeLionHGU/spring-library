package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.request.BookRequest;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private int publicationYear;

    @Column(nullable = false)
    private String classification;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private int amount;

    public static Book toBook(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.isStatus())
                .amount(bookRequest.getAmount())
                .build();
    }

    public void update(BookRequest bookRequest) {
        this.title = bookRequest.getTitle();
        this.author = bookRequest.getAuthor();
        this.publisher = bookRequest.getPublisher();
        this.publicationYear = bookRequest.getPublicationYear();
        this.classification = bookRequest.getClassification();
        this.status = bookRequest.isStatus();
        this.amount = bookRequest.getAmount();
    }

    public void update(int amount){
        this.amount = amount;
    }
}
