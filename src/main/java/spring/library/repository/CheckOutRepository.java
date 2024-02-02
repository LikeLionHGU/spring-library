package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.CheckOut;

import java.util.List;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {
    List<CheckOut> findByMember_MemberId(Long memberId);
    CheckOut findByCheckoutIdAndMember_MemberId(Long checkoutId, Long memberId);

}