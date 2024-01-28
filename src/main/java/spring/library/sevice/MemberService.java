package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.exception.IdNumberAlreadyExistsException;
import spring.library.repository.MemberRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void registerMember(MemberDto memberDto) {
        memberRepository.findByIdNumber(memberDto.getIdNumber())
                .ifPresent(member -> {
                    throw new IdNumberAlreadyExistsException();
                });
        memberRepository.save(Member.from(memberDto));
    }
}
