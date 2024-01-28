package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.request.MemberRequest;
import spring.library.dto.response.MemberListResponse;
import spring.library.exception.DuplicateMemberException;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Long createMember(MemberRequest request) {
        if (memberRepository.existsByIdNumber(request.getIdNumber())) {
            throw new DuplicateMemberException();
        }
        Member member = memberRepository.save(Member.toMember(request));
        return member.getMemberId();
    }
    public MemberListResponse getMemberList() {
        List<Member> members = memberRepository.findAll();
        return new MemberListResponse(members);
    }
    public void updateMember(Long memberId, MemberRequest from) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        member.update(from);
        memberRepository.save(member);
    }
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
