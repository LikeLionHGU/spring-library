package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.library.domain.PurchaseRequest;

import java.util.List;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
    @Query("select p from PurchaseRequest p where p.member.memberId = :memberId")
    List<PurchaseRequest> findAllByMemberId(Long memberId);
}
