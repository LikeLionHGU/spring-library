package spring.library.repository;

import spring.library.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.name = :username and m.idNumber = :password")
    Optional<Member> findMemberByUsernameAndPassword(String username, String password);

    Optional<Member> findByIdNumber(long idNumber);
    Optional<Member> findByName(String name);
}
