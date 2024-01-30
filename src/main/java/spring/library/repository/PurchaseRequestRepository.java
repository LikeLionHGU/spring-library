package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.PurchaseRequest;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
}
