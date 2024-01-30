package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberToBookService {
	private final BookRepository bookRepository;

	// todo : 관리자가 도서 등록하는 것
	public Book save(BookDto bookDto){
		return bookRepository.save(Book.toBookByBookDto(bookDto));
	}

	// todo : 관리자에게 도서 목록 전부 보여주는 것   ||  ☆ 회원도 이 기능 사용 가능하도록 해야 한다.
	public List<Book> findAll(){
		return bookRepository.findAll();
	}

	// todo : 관리자가 도서 정보 수정하는 것
	@Transactional  // 이게 없으면, 수정이 안 되던데, 왜 그런 걸까?
	public Book update(Long id, BookDto bookDto){
        Book updateBook = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book update부분에서 예외 발생! 입력값 확인!"));
		updateBook.update(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPublisher(), bookDto.getPublicationYear(), bookDto.getClassification());
		return updateBook;
	}

	
	// todo: 관리자가 도서 삭제하는 것
	public void delete(Long id){
		bookRepository.deleteById(id);
	}

}
