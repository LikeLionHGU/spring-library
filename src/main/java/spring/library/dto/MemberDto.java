package spring.library.dto;

;import spring.library.controller.form.MemberSignUpForm;
import spring.library.domain.Book;
import spring.library.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;
    private String name;
    private long idNumber;
    private String feature;
    private String phoneNumber;
    private int bookCount;


    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .idNumber(member.getIdNumber())
                .feature(member.getFeature())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    public static MemberDto from(MemberSignUpForm MemerSignUpForm) { // 멤버 등록
        return MemberDto.builder()
                .memberId(MemerSignUpForm.getMemberId())
                .name(MemerSignUpForm.getName())
                .idNumber(MemerSignUpForm.getIdNumber())
                .feature(MemerSignUpForm.getFeature())
                .phoneNumber(MemerSignUpForm.getPhoneNumber())
                .build();

    }

}
