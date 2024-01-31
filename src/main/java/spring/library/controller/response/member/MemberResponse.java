package spring.library.controller.response.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Feature;
import spring.library.controller.request.MemberRequest;
import spring.library.controller.response.ApiResponse;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse extends ApiResponse {
    private String name;
    private String email;
    private String idNumber;
    private String feature;
    private String phoneNumber;

    public MemberResponse(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.idNumber = member.getIdNumber();
        this.feature = member.getFeature().toString();
        this.phoneNumber = member.getPhoneNumber();
    }
}
