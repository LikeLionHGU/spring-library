package spring.library.exception;

public class ReturnUnable extends RuntimeException {

    private static final String RETURN_UNABLE = "대출현황을 다시 확인해주세요";

    public ReturnUnable() {
        super(RETURN_UNABLE);
    }
}
