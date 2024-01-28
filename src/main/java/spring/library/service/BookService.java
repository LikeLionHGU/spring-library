package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.dto.request.BookRequest;
import spring.library.dto.request.MemberRequest;
import spring.library.dto.response.book.BookListResponse;
import spring.library.exception.BookNotFoundException;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.BookRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public Long createBook(BookRequest request) {
        Book existingBook = bookRepository.findByTitleAndAuthorAndPublisherAndPublicationYearAndClassification(
                request.getTitle(), request.getAuthor(), request.getPublisher(), request.getPublicationYear(), request.getClassification());

        if (existingBook != null) {
            existingBook.update(existingBook.getAmount() + 1);
            bookRepository.save(existingBook);
            return existingBook.getBookId();
        } else {
            Book newBook = bookRepository.save(Book.toBook(request));
            return newBook.getBookId();
        }
    }

    public BookListResponse getBookList() {
        List<Book> books = bookRepository.findAll();
        return new BookListResponse(books);
    }

    public void updateBook(Long bookId, BookRequest from) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException());
        book.update(from);
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
