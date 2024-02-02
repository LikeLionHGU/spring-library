package spring.library.repository;

import org.springframework.data.domain.Pageable;
import spring.library.domain.Book;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    @Query("select b from Book b where b.title = :title and b.author = :author")
    Optional<Book> findBookByTitleAndAuthor(String title, String author);
    Optional<Book> findBookByAuthor(String author);
    Optional<Book> findByTitle(String title);
}




