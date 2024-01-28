package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.CheckoutList;

@Repository
public interface CheckoutListRepository extends JpaRepository<CheckoutList, Long> {
}
