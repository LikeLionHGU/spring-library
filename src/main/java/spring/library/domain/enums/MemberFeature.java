package spring.library.domain.enums;

import lombok.Getter;
import spring.library.exception.InvalidEnumValueException;

@Getter
public enum MemberFeature {
    STUDENT("학생"),
    ADMIN("관리자");

    private final String feature;

    MemberFeature(String feature){
        this.feature = feature;
    }

    public static MemberFeature from(String feature){
        if(feature.equals("학생")){
            return STUDENT;
        }else if(feature.equals("관리자")){
            return ADMIN;
        }else{
            throw new InvalidEnumValueException("유효하지 않은 회원 유형입니다.");
        }
    }
}
