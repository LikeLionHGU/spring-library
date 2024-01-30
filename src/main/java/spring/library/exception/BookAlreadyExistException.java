package spring.library.exception;

public class BookAlreadyExistException extends RuntimeException{
    private static final String MESSAGE = "이미 존재하는 책입니다.";

    public BookAlreadyExistException(){
        super(MESSAGE);
    }
}
