package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.History;
import spring.library.domain.Member;
import spring.library.dto.BookDto;
import spring.library.dto.HistoryDto;
import spring.library.dto.MemberDto;
import spring.library.exception.BorrowUnable;
import spring.library.exception.ReturnUnable;
import spring.library.repository.BookRepository;
import spring.library.repository.HistoryRepository;
import spring.library.repository.MemberRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckoutService {

  private final MemberRepository memberRepository;
  private final BookRepository bookRepository;
  private final HistoryRepository historyRepository;

  // TO-Do
  // 1. 빌리기
  //  1) 학생/관리자/교사 확인 -0
  //  2) 대출가능상태인지 확인 -0
  //  3) 대출 중인 책 몇 권인지 확인 -0
  //  4) 책에 해당 유저 Id 넣고, 빌린 책 ++
  //  5) 반납일 : 현재~10일
  // +6) History에도 담기

  public void checkOut(MemberDto memberDto, BookDto bookDto) {
    Member member = memberRepository.findByFeature(memberDto.getMemberId());
    Optional<Book> book = bookRepository.findById(bookDto.getBookId());

    History history = History.toHistory(member, book.get());


    int borrowMaxNum = 0;
    int duration = 0;

    switch (member.getFeature()) {
      case "학생" -> {
        borrowMaxNum = 10;
        duration += 10;
      }
      case "교사" -> {
        borrowMaxNum = 20;
        duration += 30;
      }
      case "관리자" -> {
        borrowMaxNum = 100;
        duration += 110813;
      }
    }

    if (bookDto.getStatus().equals("대출가능") && member.getNumberBooks() < borrowMaxNum) {
      book.get().setMember(member);


      member.setNumberBooks(member.getNumberBooks() + 1);
      book.get().setStatus("대출중");
      history.setDue(LocalDate.now().plusDays(duration));


      bookRepository.save(book.get());
      memberRepository.save(member);

      historyRepository.save(history);


    } else {
      throw new BorrowUnable();
    }
  }



  // To-Do
  // 2. 빌린 책 확인
  //    1) 회원 id받기
  //    2) 그 회원 id로 책 mapping
  public List<BookDto> getBookTakenByMember(Long memberId){
    List<Book> books = bookRepository.findBookByMemberId(memberId);

    return books.stream().map(BookDto::from).toList();
  }


  //To - Do
  // 빌렸던 책들 다 조회
  // 1) 회원 id 받기
  // 2) History에 그 회원 id에 해당하는 책 이름 갖고오기
  // 3) List로 리턴

  public List<BookDto> getBookHistory(Long memberId){
    List<History> histories = historyRepository.findAllByMember(memberId);
    List<Book> books = histories.stream().map(History::getBook).toList();

    return books.stream().map(BookDto::from).toList();
  }


  // To-Do
  // 3. 반납
  //  1) 대출 중인지 확인
  //  2) 반납 후 빌린 책 -- /  상태 = 대출가능

  public void returnBook(MemberDto memberDto, BookDto bookDto) {
    Member member = memberRepository.findByFeature(memberDto.getMemberId());
    Optional<Book> book = bookRepository.findById(bookDto.getBookId());



    if (bookDto.getStatus().equals("대출중")) {
        book.get().setMember(null);


      member.setNumberBooks(member.getNumberBooks() - 1);
      book.get().setStatus("대출가능");

      bookRepository.save(book.get());
      memberRepository.save(member);
    } else {
      throw new ReturnUnable();
    }
  }


  // To-Do
  // 4. 연장
  //  1) 반납 예정일인지 && 한 번이라도 했는지
  //  2)

}
