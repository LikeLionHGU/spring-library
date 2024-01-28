package spring.library.exception;

public class IdPresenceException extends RuntimeException{

        private static final String MESSAGE = "존재하지 않는 아이다입니다.";

        public IdPresenceException() {
            super(MESSAGE);
        }
}
