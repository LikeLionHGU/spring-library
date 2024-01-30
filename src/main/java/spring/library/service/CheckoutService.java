package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.dto.BookDto;
import spring.library.dto.MemberDto;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckoutService {

  private final MemberRepository memberRepository;
  private final BookRepository bookRepository;

  // TO-Do
  // 1. 빌리기
  //  1) 학생/관리자/교사 확인 -0
  //  2) 대출가능상태인지 확인 -0
  //  3) 대출 중인 책 몇 권인지 확인 -0
  //  4) 책에 해당 유저 Id 넣고, 빌린 책 ++
  public Long checkOut(MemberDto memberDto, BookDto bookDto) {
    Member member = memberRepository.findByFeature(memberDto.getMemberId());
    Optional<Book> book = bookRepository.findById(bookDto.getBookId());

    if (member.getFeature().equals("학생")) {
      if (bookDto.getStatus().equals("대출가능") && member.getNumberBooks() < 10) {

      }

    }
  }

  // To-Do
  // 2. 빌린 책 확인
  //  1) 회원 id받기
  //  2) 그 회원 id로 책 mapping

  // To-Do
  // 3. 반납
  //  1) 대출 중인지 확인
  //  2) 반납 후 빌린 책 --

  // To-Do
  // 4. 연장
  //  1) 반납 예정일인지 && 한 번이라도 했는지
  //  2)

}
