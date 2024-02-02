package spring.library.exception;

public class CheckoutRenewalCountLimitException extends RuntimeException{
    private static final String MESSAGE = "연장 횟수를 초과하였습니다.";

    public CheckoutRenewalCountLimitException() {
        super(MESSAGE);
    }
}
