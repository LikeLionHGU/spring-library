package spring.library.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.MemberForm;
import spring.library.dto.request.MemberRequest;
import spring.library.dto.response.ApiResponse;
import spring.library.dto.response.MemberIdResponse;
import spring.library.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse> createMember(@RequestBody MemberForm form){
        System.out.println("MemberController.createMember");
        Long memberId = memberService.createMember(MemberRequest.from(form));
        ApiResponse response = new MemberIdResponse(memberId);
        return ResponseEntity.ok(response);
    }
}
