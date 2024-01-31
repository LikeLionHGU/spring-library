package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Book;
import spring.library.domain.RentalManagement;

public interface BookHistoryRepository extends JpaRepository<RentalManagement, Long> {}
