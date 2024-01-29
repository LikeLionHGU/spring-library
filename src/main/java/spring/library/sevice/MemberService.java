package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.exception.IdNumberAlreadyExistsException;
import spring.library.exception.IdPresenceException;
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
                .orElseThrow(IdPresenceException::new);
        validateIdNumberDuplication(memberDto.getIdNumber(), memberId);
        member.updateAll(memberDto);
    }

    private void validateIdNumberDuplication(int idNumber, Long memberId) {
        memberRepository.findByIdNumber(idNumber)
                .ifPresent(member -> {
                    if (!member.getMemberId().equals(memberId)) {
                        throw new IdNumberAlreadyExistsException();
                    }
                });
    }

    private void validateIdNumberDuplication(int idNumber) {
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
                .orElseThrow(IdPresenceException::new);
    }
}
