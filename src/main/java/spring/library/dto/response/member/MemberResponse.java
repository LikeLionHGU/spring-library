package spring.library.dto.response.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Feature;
import spring.library.dto.request.MemberRequest;
import spring.library.dto.response.ApiResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse extends ApiResponse {
    private String name;
    private String email;
    private String idNumber;
    private Feature feature;
    private String phoneNumber;

    public MemberResponse(MemberRequest memberRequest) {
        this.name = memberRequest.getName();
        this.email = memberRequest.getEmail();
        this.idNumber = memberRequest.getIdNumber();
        this.feature = memberRequest.getFeature();
        this.phoneNumber = memberRequest.getPhoneNumber();
    }
}
