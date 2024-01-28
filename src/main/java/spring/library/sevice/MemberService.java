package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.exception.IdNumberAlreadyExistsException;
import spring.library.exception.MemberIdPresenceException;
import spring.library.repository.MemberRepository;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void registerMember(MemberDto memberDto) {
        validateIdNumberDuplication(memberDto.getIdNumber());
        memberRepository.save(Member.from(memberDto));
    }

    public List<MemberDto> getMembers() {
        return memberRepository.findAll().stream()
                .map(MemberDto::from)
                .toList();
    }

    public void updateMember(Long memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberIdPresenceException::new);
        member.updateAll(memberDto);
    }

    private void validateIdNumberDuplication(String idNumber) {
        memberRepository.findByIdNumber(idNumber)
                .ifPresent(member -> {
                    throw new IdNumberAlreadyExistsException();
                });
    }

    public void deleteMember(Long memberId) {
        ValidateIdPresence(memberId);
        memberRepository.deleteById(memberId);
    }

    public void ValidateIdPresence(Long memberId){
        memberRepository.findById(memberId)
                .orElseThrow(MemberIdPresenceException::new);
    }
}
