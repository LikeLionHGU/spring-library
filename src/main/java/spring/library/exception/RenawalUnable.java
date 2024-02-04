package spring.library.exception;

public class RenawalUnable extends RuntimeException {

    private static final String RENEWAL_UNABLE = "반납일에만 연장할 수 있습니다.";

    public RenawalUnable() {
        super(RENEWAL_UNABLE);
    }
}
