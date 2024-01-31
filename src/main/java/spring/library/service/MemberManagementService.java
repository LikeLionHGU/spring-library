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


	// todo: 관리자가 회원 등록하는 것
	public Member save(MemberDto memberDto){
		return memberRepository.save(Member.toMember(memberDto));
	}

	// todo: 관리자가 등록된 회원 목록 조회하는 것
	public List<Member> findAll(){
		return memberRepository.findAll();
	}

	// todo: 관리자가 회원 정보 수정하는 것
	@Transactional
	public Member update(Long id, MemberDto memberDto){
		Member updateOrdinary = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + "는 없는 id입니다."));
//		updateOrdinary.update(id, ordinaryDto);     -> 이렇게 하면 값이 바뀌지 않아. 왜 그런지 질문하기.
		updateOrdinary.update(memberDto.getName(), memberDto.getIdNumber(), memberDto.getFeature(), memberDto.getEmail(), memberDto.getPhoneNumber());
		return updateOrdinary;
	}

	// todo; 관리자가 회원 목록 삭제하는 것
	public void delete(Long memberId){
		memberRepository.deleteById(memberId);
	}

}
