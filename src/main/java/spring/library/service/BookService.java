package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;
import spring.library.exception.IdPresenceException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void registerBook(BookDto bookDto) {
        bookRepository.save(Book.from(bookDto));
    }

    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream()
                .map(BookDto::from)
                .toList();
    }

    public void deleteBook(Long bookId) {
        ValidateIdPresence(bookId);
        bookRepository.deleteById(bookId);
    }

    private Book ValidateIdPresence(Long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IdPresenceException("존재하지 않는 도서입니다."));
    }

    public void updateBook(Long bookId, BookDto bookDto) {
        Book book = ValidateIdPresence(bookId);
        book.updateBookExceptStatus(bookDto);
    }
}
