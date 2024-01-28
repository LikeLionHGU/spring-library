package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long addMember(MemberDto memberDto) {
        Member member = memberRepository.save(Member.toMember(memberDto));
        return member.getMemberId();
    }

    public MemberDto getMemberInfo(String memberName) {
        System.out.println("memberId = " + memberName);
        Member member =
                memberRepository
                        .findByUsername(memberName)
                        .orElseThrow(MemberNotFoundException::new);
        return MemberDto.from(member);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }


}
