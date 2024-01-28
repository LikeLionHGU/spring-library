package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.request.MemberRequest;
import spring.library.exception.DuplicateMemberException;
import spring.library.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Long createMember(MemberRequest request) {
        if (memberRepository.existsByIdNumber(request.getIdNumber())) {
            throw new DuplicateMemberException("이미 존재하는 회원입니다.");
        }
        Member member = memberRepository.save(Member.toMember(request));
        return member.getMemberId();
    }
}
