package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select b from Book b join fetch b.member where b.member = :memberId")
    List<Book> findBookByMemberId(Long memberId);

    @Query("select b from Book b where b.status ='대출가능' and b.bookId = :bookId")
    Book findBookByStatus(Long bookId);


}
