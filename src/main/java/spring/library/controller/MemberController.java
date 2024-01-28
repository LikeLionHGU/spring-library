package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.MemberForm;
import spring.library.dto.request.MemberRequest;
import spring.library.dto.response.ApiResponse;
import spring.library.dto.response.member.MemberIdResponse;
import spring.library.dto.response.member.MemberListResponse;
import spring.library.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse> createMember(@RequestBody MemberForm form){
        Long memberId = memberService.createMember(MemberRequest.from(form));
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<MemberListResponse> getMemberList() {
        MemberListResponse memberListResponse = memberService.getMemberList();
        return ResponseEntity.ok(memberListResponse);
    }
    @PutMapping("/{memberId}")
    public ResponseEntity<ApiResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberForm form){
        memberService.updateMember(memberId, MemberRequest.from(form));
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }
}
