package spring.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.MemberDto;

import java.util.List;

@Getter
public class MemberListResponse {

    private final List<Member> members;

    public MemberListResponse(List<MemberDto> memberDtos){
        this.members = memberDtos.stream()
                .map(Member::from)
                .toList();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Member{
        private Long memberId;
        private String name;
        private String idNumber;
        private String feature;
        private String email;
        private String phoneNumber;

        public static Member from(MemberDto memberDto){
            return Member.builder()
                    .memberId(memberDto.getMemberId())
                    .name(memberDto.getName())
                    .idNumber(memberDto.getIdNumber())
                    .feature(memberDto.getFeature())
                    .email(memberDto.getEmail())
                    .phoneNumber(memberDto.getPhoneNumber())
                    .build();
        }
    }
}
