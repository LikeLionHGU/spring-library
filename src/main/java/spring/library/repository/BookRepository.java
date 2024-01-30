package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthorAndPublisher(String title, String author, String publisher);

    Book findByTitleAndAuthorAndPublisherAndPublicationYear(String title, String author, String publisher, int publicationYear);
}
