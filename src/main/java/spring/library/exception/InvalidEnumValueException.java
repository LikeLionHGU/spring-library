package spring.library.exception;

public class InvalidEnumValueException extends RuntimeException{
        public InvalidEnumValueException(String message) {
            super(message);
        }
}
