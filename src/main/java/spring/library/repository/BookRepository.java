package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthorAndPublisherAndPublicationYearAndClassification(String title, String author, String publisher, int publicationYear, String classification);
}
