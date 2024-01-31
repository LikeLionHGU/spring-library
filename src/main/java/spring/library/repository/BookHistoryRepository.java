package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Book;
import spring.library.domain.RentalManagement;

import java.util.List;

public interface BookHistoryRepository extends JpaRepository<RentalManagement, Long> {
	List<RentalManagement> findByRentMemberId(Long rentMemberId);
}
