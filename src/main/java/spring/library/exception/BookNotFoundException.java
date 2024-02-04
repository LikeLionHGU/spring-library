package spring.library.exception;

public class BookNotFoundException extends RuntimeException {

    private static final String MEMBER_NOT_FOUND = "일치하는 도서 정보가 없습니다.";
    public BookNotFoundException() {
        super(MEMBER_NOT_FOUND);
    }

}
