package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;
import spring.library.domain.Checkout;
import spring.library.domain.Member;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    List<Checkout> findByMember_MemberId(Long memberId);
    Checkout findByCheckoutIdAndMember_MemberId(Long checkoutId, Long memberId);
}
