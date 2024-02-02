package spring.library.exception;

public class BookNotFoundException extends RuntimeException{

    private static final String BOOK_NOT_FOUND = "일치하는 책의 정보가 없습니다.";

    public BookNotFoundException() {
        super(BOOK_NOT_FOUND);
    }
}
