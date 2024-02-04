package spring.library.exception;

public class BorrowUnable extends RuntimeException {

    private static final String BORROW_UNABLE = "대출할 수 없습니다.";
    public BorrowUnable() {
        super(BORROW_UNABLE);
    }

}
