package spring.library.service;


import spring.library.domain.Book;
import spring.library.dto.BookDto;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import spring.library.controller.response.BookListResponse;
import spring.library.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;



    // 도서 조회
    public BookListResponse getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return new BookListResponse(books);
    }

    // 도서 추가
    public Long addBook(BookDto bookDto) { // 책 등록
        Book book = bookRepository.save(Book.toBook(bookDto));
        return book.getBookId();
    }
    //도서 삭제
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    // 도서 수정
    public void updateBook(Long bookId,  BookDto bookDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException());
        book.update(bookDto);
        bookRepository.save(book);
    }




}
