package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Book;
import spring.library.domain.CheckoutList;
import spring.library.domain.Member;
import spring.library.exception.IdPresenceException;
import spring.library.repository.BookRepository;
import spring.library.repository.CheckoutListRepository;
import spring.library.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckoutListService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final CheckoutListRepository checkoutListRepository;

    public void checkoutBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                IdPresenceException::new
        );
        Book book = bookRepository.findById(bookId).orElseThrow(
                IdPresenceException::new
        );
        checkoutListRepository.save(CheckoutList.from(member, book));
    }
}
