package spring.library.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Feature;

@Getter
@NoArgsConstructor
public class MemberForm {
    private String name;
    private String email;
    private String idNumber;
    private Feature feature;
    private String phoneNumber;
}
