package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Member;
import spring.library.domain.Purchase;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Purchase findByMemberAndTitleAndAuthorAndPublisherAndPublicationYear(Member member, String title, String author, String publisher, int publicationYear);
    List<Purchase> findByMember_MemberId(Long memberId);
}
