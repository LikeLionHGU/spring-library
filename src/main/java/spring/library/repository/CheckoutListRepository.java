package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;
import spring.library.domain.CheckoutList;

import java.util.List;

@Repository
public interface CheckoutListRepository extends JpaRepository<CheckoutList, Long> {
    List<CheckoutList> findAllByBook(Book book);
}
