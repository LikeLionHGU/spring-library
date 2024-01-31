package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.controller.request.BookRequest;
import spring.library.controller.response.book.BookListResponse;
import spring.library.dto.BookDto;
import spring.library.exception.BookNotFoundException;
import spring.library.exception.DuplicateBookException;
import spring.library.repository.BookRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public Long createBook(BookDto bookDto) {
        if(bookRepository.findByTitleAndAuthorAndPublisherAndPublicationYear(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPublisher(), bookDto.getPublicationYear()) != null){
            throw new DuplicateBookException();
        }
        Book book = bookRepository.save(Book.from(bookDto));
        return book.getBookId();
    }

    public BookListResponse getBookList() {
        List<Book> books = bookRepository.findAll();
        return new BookListResponse(books);
    }

    public void updateBook(Long bookId, BookDto bookDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException());
        book.update(bookDto);
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
