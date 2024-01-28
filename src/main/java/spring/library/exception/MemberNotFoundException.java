package spring.library.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException() {
        super("[ERROR] 멤버를 발견하지 못했습니다.");
    }
}
