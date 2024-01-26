package spring.library.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberForm {
    private String name;
    private Long idNumber;

    private String feature;

    private String email;

    private String phoneNumber;

}
