package spring.library.exception;

public class BookIsUnavailableException extends RuntimeException {
    private static final String MESSAGE = "대출 중인 도서입니다.";

    public BookIsUnavailableException() {
        super(MESSAGE);
    }
}
