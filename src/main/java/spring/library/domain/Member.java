package spring.library.domain;

import jakarta.persistence.*;
import spring.library.domain.enums.MemberFeature;

@Entity
public class Member extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "id_number", length = 30, nullable = false)
    private String idNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "feature", nullable = false)
    private MemberFeature feature=MemberFeature.STUDENT;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 30, nullable = false)
    private String phoneNumber;
}
