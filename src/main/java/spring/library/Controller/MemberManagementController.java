package spring.library.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.service.MemberManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/members")
public class MemberManagementController {

	private final MemberManagementService memberToOtherService;

	@PostMapping
	public ResponseEntity<Member> addMember(
		@RequestBody MemberDto memberDto){
		Member saveOrdinary = memberToOtherService.save(memberDto);
		return ResponseEntity.ok().body(saveOrdinary);
	}

	@GetMapping
	public ResponseEntity<List<MemberDto>> findAllOrdinaries(){
		List<MemberDto> Ordinaries = memberToOtherService.findAll()
			.stream()
			.map(MemberDto::new)
			.toList();
		return ResponseEntity.ok().body(Ordinaries);
	}


	@PutMapping("/{memberId}")
	public ResponseEntity<Member> updateOrdinary(
		@PathVariable Long memberId, @RequestBody MemberDto updateOrdinaryDto){
		Member ordinary = memberToOtherService.update(memberId, updateOrdinaryDto);
		return ResponseEntity.ok().body(ordinary);
	}


  	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> deleteOrdinary(
		@PathVariable Long memberId){
		memberToOtherService.delete(memberId);
		return ResponseEntity.ok().build();
    }

}
