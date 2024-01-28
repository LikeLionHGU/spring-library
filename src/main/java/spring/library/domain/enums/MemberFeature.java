package spring.library.domain.enums;

import lombok.Getter;

@Getter
public enum MemberFeature {
    STUDENT("학생"),
    ADMIN("관리자");

    private final String feature;

    MemberFeature(String feature){
        this.feature = feature;
    }

    public static MemberFeature getMemberFeature(String feature){
        for(MemberFeature memberFeature : MemberFeature.values()){
            if(memberFeature.getFeature().equals(feature)){
                return memberFeature;
            }
        }
        return null;
    }
}
