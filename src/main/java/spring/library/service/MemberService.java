package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long addMember(MemberDto memberDto) {
        Member member = memberRepository.save(Member.toMember(memberDto));
        return member.getMemberId();
    }

    public MemberDto getMemberInfo(Long memberId) {
        Member member =
                memberRepository
                        .findById(memberId)
                        .orElseThrow(MemberNotFoundException::new);
        return MemberDto.from(member);
    }

    public List<MemberDto> getAllMembers(){
        List<Member> members = memberRepository.findAll();

        return members.stream().map(MemberDto::from).toList();
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }


}
