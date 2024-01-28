package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.dto.BookDto;
import spring.library.dto.MemberDto;
import spring.library.exception.BookNotFoundException;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;


    public Long addBook(BookDto bookDto){
//        Member member = memberRepository.findById(bookDto.getBookId()).orElseThrow(MemberNotFoundException::new);
        Book book = bookRepository.save(Book.toBook(bookDto));
//        Book book = bookRepository.save(Book.toBook(bookDto,member));

        return book.getBookId();
    }

    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookDto::from).toList();
    }

    public BookDto getBook(Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        return BookDto.from(book);
    }

    public void  deleteBook(Long bookId){
        bookRepository.deleteById(bookId);

    }

    public long countAllBook(){
        return bookRepository.count();
    }



}
