package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Book;
import spring.library.domain.enums.BookClassification;
import spring.library.domain.enums.BookStatus;
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
        bookRepository.findByTitleAndAuthorAndPublisherAndPublicationYearAndClassificationAndStatus(
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getPublisher(),
                        bookDto.getPublicationYear(),
                        BookClassification.from(bookDto.getClassification()),
                        BookStatus.from(bookDto.getStatus()))
                .ifPresentOrElse(
                        book -> book.addAmount(bookDto.getAmount()),
                        () -> bookRepository.save(Book.from(bookDto)));
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

    private void ValidateIdPresence(Long bookId){
        bookRepository.findById(bookId)
                .orElseThrow(IdPresenceException::new);
    }
}
