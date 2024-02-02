package spring.library.service;

import spring.library.exception.MemberNotFoundException;
import spring.library.exception.DuplicateMemberException;
import spring.library.repository.MemberRepository;
import spring.library.controller.response.MemberListResponse;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 등록
    public Long signUpMember(MemberDto memberDto) {

        Optional<Member> existingMember = memberRepository.findByIdNumber(memberDto.getIdNumber());

        if (existingMember.isPresent()) {
            throw new DuplicateMemberException(); //isPresent()는 Optional 클래스의 메서드 중 하나입니다. 이 메서드는 해당 Optional 객체가 값으로 채워져 있는지 여부를 확인하는 데 사용됩니다. 즉, 값이 존재하면 true를 반환하고, 값이 없으면 false를 반환합니다.ㅌ
        }

        Member member = memberRepository.save(Member.toMember(memberDto));
        return member.getMemberId();
    }

    // 회원 조회
    public MemberListResponse getMemberList(){
        List<Member> members = memberRepository.findAll();
        return new MemberListResponse(members);
    }

    // 회원 삭제
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }


    // 회원 수정
    public void updateMember(Long memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        member.update(memberDto);
        memberRepository.save(member);
    }




}
