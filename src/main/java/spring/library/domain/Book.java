package spring.library.domain;

import jakarta.persistence.*;
import spring.library.domain.enums.BookClassification;
import spring.library.domain.enums.BookStatus;

@Entity
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
    private BookClassification bookClassification=BookClassification.ETC;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

    @Column(name = "amount", nullable = false)
    private int amount;
}
