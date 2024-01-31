package spring.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProcessResult {
    APPLY("신청"),
    CANCEL("취소"),
    REGISTER("등록");

    private String processResult;

    public static ProcessResult from(String processResult) {
        for (ProcessResult p : ProcessResult.values()) {
            if (p.processResult.equals(processResult)) {
                return p;
            }
        }
        throw new IllegalArgumentException();
    }
}
