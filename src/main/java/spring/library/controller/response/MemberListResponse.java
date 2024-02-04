package spring.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.MemberDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)

public class MemberListResponse extends ApiResponse {
    private List<MemberDto> member;


    public MemberListResponse(List<MemberDto> memberDto){
        this.member = memberDto;
    }
}
