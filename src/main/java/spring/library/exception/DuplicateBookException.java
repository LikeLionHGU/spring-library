package spring.library.exception;

public class DuplicateBookException extends RuntimeException{
    public DuplicateBookException() {
        super("이미 존재하는 책입니다.");
    }
}
