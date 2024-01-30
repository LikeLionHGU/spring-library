package spring.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Feature {
//    학생, 교직원, 관리자

    STUDENT("학생", 10),
    STAFF("교직원", 30),
    ADMIN("관리자", 110813);

    private String feature;
    private int borrowLimit;

    public static Feature from(String feature) {
        for (Feature f : Feature.values()) {
            if (f.feature.equals(feature)) {
                return f;
            }
        }
        throw new IllegalArgumentException();
    }
}
