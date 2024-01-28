package spring.library.dto.request;

import lombok.*;
import spring.library.controller.form.MemberForm;
import spring.library.domain.Feature;
import spring.library.domain.Member;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String name;
    private String email;
    private String idNumber;
    private Feature feature;
    private String phoneNumber;

    public static MemberRequest from(Member member) {
        return MemberRequest.builder()
                .name(member.getName())
                .email(member.getEmail())
                .idNumber(member.getIdNumber())
                .feature(member.getFeature())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static MemberRequest from(MemberForm memberForm) {
        return MemberRequest.builder()
                .name(memberForm.getName())
                .email(memberForm.getEmail())
                .idNumber(memberForm.getIdNumber())
                .feature(memberForm.getFeature())
                .phoneNumber(memberForm.getPhoneNumber())
                .build();
    }
}
