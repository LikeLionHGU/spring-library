package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;
import spring.library.domain.enums.BookClassification;
import spring.library.domain.enums.BookStatus;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthorAndPublisherAndPublicationYearAndClassificationAndStatus(String title, String author, String publisher, int publicationYear, BookClassification classification, BookStatus status);
}
