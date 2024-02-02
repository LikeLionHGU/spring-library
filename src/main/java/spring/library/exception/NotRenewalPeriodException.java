package spring.library.exception;

public class NotRenewalPeriodException extends RuntimeException{
    private static final String MESSAGE = "오늘은 반납 기간이 아닙니다.";

    public NotRenewalPeriodException() {
        super(MESSAGE);
    }
}
