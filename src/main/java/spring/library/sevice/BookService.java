package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Book;
import spring.library.domain.enums.BookClassification;
import spring.library.domain.enums.BookStatus;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;

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
}
