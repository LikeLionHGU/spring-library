package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.controller.response.member.MemberResponse;
import spring.library.domain.Member;
import spring.library.controller.request.MemberRequest;
import spring.library.controller.response.member.MemberListResponse;
import spring.library.dto.MemberDto;
import spring.library.exception.DuplicateMemberException;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Long createMember(MemberDto memberDto) {
        if(memberRepository.findByIdNumber(memberDto.getIdNumber()) != null){
            throw new DuplicateMemberException();
        }
        Member member = memberRepository.save(Member.from(memberDto));
        return member.getMemberId();
    }
    public MemberListResponse getMemberList() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> memberResponses = members.stream()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
        return new MemberListResponse(memberResponses);
    }
    public void updateMember(Long memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        member.update(memberDto);
        memberRepository.save(member);
    }
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
