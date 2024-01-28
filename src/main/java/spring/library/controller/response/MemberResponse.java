package spring.library.controller.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.MemberDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    private String name;
    private String feature;
    private String email;
    private String phoneNumber;
    private int numberBooks;

    public MemberResponse(MemberDto memberDto){
        this.name=memberDto.getName();
        this.feature=memberDto.getFeature();
        this.email=memberDto.getEmail();
        this.phoneNumber=memberDto.getPhoneNumber();
        this.numberBooks=memberDto.getNumberBooks();

    }
}
