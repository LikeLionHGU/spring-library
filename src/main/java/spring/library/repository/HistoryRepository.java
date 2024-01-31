package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.library.domain.Book;
import spring.library.domain.History;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("select h from History h where h.member.memberId = :memberId")
    List<History> findAllByMember(Long memberId);

    @Query("select h from History h where h.member.memberId = :memberId and h.book.bookId = :bookId")
    Optional<History> findByMemberAndBook(Long memberId, Long bookId);

}
