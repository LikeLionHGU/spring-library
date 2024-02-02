package spring.library.controller.request;

import lombok.*;
import spring.library.domain.Feature;

@Getter
@NoArgsConstructor
public class MemberRequest {
    private String name;
    private String email;
    private String idNumber;
    private String feature;
    private String phoneNumber;
}
