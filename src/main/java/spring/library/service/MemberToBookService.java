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

	public Book save(BookDto bookDto){
		return bookRepository.save(Book.toBookByBookDto(bookDto));
	}

	public List<Book> findAll(){
		return bookRepository.findAll();
	}

	@Transactional
	public Book update(Long id, BookDto bookDto){
        Book updateBook = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book update부분에서 예외 발생! 입력값 확인!"));
		updateBook.update(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPublisher(), bookDto.getPublicationYear(), bookDto.getClassification());
		return updateBook;
	}

	
	public void delete(Long id){
		bookRepository.deleteById(id);
	}

}
