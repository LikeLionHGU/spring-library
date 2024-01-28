package spring.library.domain.enums;

import lombok.Getter;
import spring.library.exception.InvalidEnumValueException;

@Getter
public enum MemberFeature {
    STUDENT("학생",10),
    Faculty("교직원",30),
    ADMIN("관리자",110813);

    private final String feature;
    private final int borrowLimit;

    MemberFeature(String feature, int borrowLimit){
        this.feature = feature;
        this.borrowLimit = borrowLimit;
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
