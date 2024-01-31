package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Book;

public interface BookHistoryReposiory extends JpaRepository<Book, Long> {}
