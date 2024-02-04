package spring.library.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.MemberForm;
import spring.library.controller.response.ApiResponse;
import spring.library.controller.response.MemberIdResponse;
import spring.library.controller.response.MemberListResponse;
import spring.library.controller.response.MemberResponse;
import spring.library.dto.MemberDto;
import spring.library.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse> addMember(@RequestBody MemberForm form){
         Long memberId = memberService.addMember(MemberDto.from(form));
         ApiResponse response = new MemberIdResponse(memberId);
         return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMemeber(){
        List<MemberDto> memberDto = memberService.getAllMembers();
        ApiResponse response = new MemberListResponse(memberDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{member_id}")
    public ResponseEntity<ApiResponse> getMember(@PathVariable Long member_id){
        MemberDto memberDto = memberService.getMemberInfo(member_id);
        ApiResponse response = new MemberResponse(memberDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{member_id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long member_id){
        memberService.deleteMember(member_id);
        ApiResponse response = new MemberIdResponse(member_id);
        return ResponseEntity.ok(response);
    }

        }