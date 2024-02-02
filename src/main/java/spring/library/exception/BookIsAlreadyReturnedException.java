package spring.library.exception;

public class BookIsAlreadyReturnedException extends RuntimeException{
    private static final String MESSAGE = "이미 반납된 도서입니다.";

    public BookIsAlreadyReturnedException() {
        super(MESSAGE);
    }
}
