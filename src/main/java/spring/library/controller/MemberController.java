package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.MemberSignUpForm;
import spring.library.controller.response.*;
import spring.library.dto.MemberDto;
import spring.library.service.MemberService;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse> addMember(@RequestBody MemberSignUpForm memberSignUpForm){
        Long memberId = memberService.signUpMember(MemberDto.from(memberSignUpForm));
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<MemberListResponse> getMemberList() {
        MemberListResponse memberListResponse = memberService.getMemberList();
        return ResponseEntity.ok(memberListResponse);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<ApiResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberSignUpForm memberSignUpForm){
        memberService.updateMember(memberId, MemberDto.from(memberSignUpForm));
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }

}
