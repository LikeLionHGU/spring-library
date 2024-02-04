package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.form.MemberForm;
import spring.library.domain.Member;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;

    private String name;
    private String feature;
    private String email;
    private String phoneNumber;
    private int numberBooks;

    public static MemberDto from(MemberForm memberForm){

        return MemberDto.builder().memberId(memberForm.getMemberId()).name(memberForm.getName())
                .feature(memberForm.getFeature()).email(memberForm.getEmail()).phoneNumber(memberForm.getPhoneNumber())
                .build();
        }

        public static MemberDto from(Member member){

        return MemberDto.builder().memberId(member.getMemberId()).name(member.getName())
                .feature(member.getFeature()).email(member.getEmail()).phoneNumber(member.getPhoneNumber())
                .numberBooks(member.getNumberBooks()).build();

        }
    }
