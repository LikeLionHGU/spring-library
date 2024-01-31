package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.MemberRequest;
import spring.library.controller.response.ApiResponse;
import spring.library.controller.response.member.MemberIdResponse;
import spring.library.controller.response.member.MemberListResponse;
import spring.library.dto.MemberDto;
import spring.library.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse> createMember(@RequestBody MemberRequest memberRequest){
        Long memberId = memberService.createMember(MemberDto.from(memberRequest));
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<MemberListResponse> getMemberList() {
        MemberListResponse memberListResponse = memberService.getMemberList();
        return ResponseEntity.ok(memberListResponse);
    }
    @PatchMapping("/{memberId}")
    public ResponseEntity<ApiResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberRequest memberRequest){
        memberService.updateMember(memberId, MemberDto.from(memberRequest));
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
