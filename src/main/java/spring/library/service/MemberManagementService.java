package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.repository.MemberRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberManagementService {

	public final MemberRepository memberRepository;

	public Member save(MemberDto memberDto){
		return memberRepository.save(Member.toMember(memberDto));
	}

	public List<Member> findAll(){
		return memberRepository.findAll();
	}

	@Transactional
	public Member update(Long id, MemberDto memberDto){
		Member updateOrdinary = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "는 없는 id입니다."));
		updateOrdinary.update(memberDto.getName(), memberDto.getIdNumber(), memberDto.getFeature(), memberDto.getEmail(), memberDto.getPhoneNumber());
		return updateOrdinary;
	}

	public void delete(Long memberId){
		memberRepository.deleteById(memberId);
	}

}
