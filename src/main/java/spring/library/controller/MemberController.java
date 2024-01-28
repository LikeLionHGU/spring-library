package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.library.controller.request.MemberRegisterRequest;
import spring.library.dto.MemberDto;
import spring.library.sevice.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public void signup(@RequestBody MemberRegisterRequest memberRegisterRequest) {
        System.out.println("memberRegisterRequest = " + memberRegisterRequest);
        MemberDto memberDto = MemberDto.from(memberRegisterRequest);
        memberService.registerMember(memberDto);
    }
}
