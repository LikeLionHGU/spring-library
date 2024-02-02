package spring.library.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRegisterRequest {
    private String name;
    private int idNumber;
    private String feature;
    private String email;
    private String phoneNumber;
}
