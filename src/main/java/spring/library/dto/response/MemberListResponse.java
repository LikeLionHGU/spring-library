package spring.library.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Member;
import spring.library.dto.request.MemberRequest;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class MemberListResponse{
    private List<Member> members;

    public MemberListResponse(List<Member> members){
        this.members = members;
    }
}
