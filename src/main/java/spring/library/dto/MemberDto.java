package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.MemberRegisterRequest;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberDto {
    private Long memberId;
    private String name;
    private String idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public static MemberDto from(MemberRegisterRequest memberRegisterRequest){
        return MemberDto.builder()
                .name(memberRegisterRequest.getName())
                .idNumber(memberRegisterRequest.getIdNumber())
                .feature(memberRegisterRequest.getFeature())
                .email(memberRegisterRequest.getEmail())
                .phoneNumber(memberRegisterRequest.getPhoneNumber())
                .build();
    }
}
