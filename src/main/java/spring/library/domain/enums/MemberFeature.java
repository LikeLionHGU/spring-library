package spring.library.domain.enums;

import lombok.Getter;
import spring.library.exception.InvalidEnumValueException;

@Getter
public enum MemberFeature {
    STUDENT("학생"),
    Faculty("교직원"),
    ADMIN("관리자");

    private final String feature;

    MemberFeature(String feature){
        this.feature = feature;
    }

    public static MemberFeature from(String feature){
        for(MemberFeature memberFeature : MemberFeature.values()){
            if(memberFeature.getFeature().equals(feature)){
                return memberFeature;
            }
        }
        throw new InvalidEnumValueException("유효하지 않은 회원 유형입니다.");
    }
}
