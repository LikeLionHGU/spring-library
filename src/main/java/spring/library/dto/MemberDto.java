package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.MemberRequest;
import spring.library.domain.Feature;
import spring.library.domain.Member;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberDto {
    private Long memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private String idNumber;
    private String feature;

    public static MemberDto from(Member member){
        return MemberDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .idNumber(member.getIdNumber())
                .feature(member.getFeature().getFeature())
                .build();
    }

    public static MemberDto from(MemberRequest memberRequest) {
        return MemberDto.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .phoneNumber(memberRequest.getPhoneNumber())
                .idNumber(memberRequest.getIdNumber())
                .feature(memberRequest.getFeature())
                .build();
    }
    public static MemberDto from(CheckoutDto checkoutDto) {
        return MemberDto.builder()
                .memberId(checkoutDto.getMemberId())
                .build();
    }
}
