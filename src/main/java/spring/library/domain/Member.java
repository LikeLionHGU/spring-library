package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.enums.MemberFeature;
import spring.library.dto.MemberDto;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "id_number", nullable = false)
    private int idNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "feature", nullable = false)
    private MemberFeature feature=MemberFeature.STUDENT;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 30, nullable = false)
    private String phoneNumber;

    public static Member from(MemberDto memberDto){
        return Member.builder()
                .name(memberDto.getName())
                .idNumber(memberDto.getIdNumber())
                .feature(MemberFeature.from(memberDto.getFeature()))
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }

    public void updateAll(MemberDto memberDto) {
        this.name = memberDto.getName();
        this.idNumber = memberDto.getIdNumber();
        this.feature = MemberFeature.from(memberDto.getFeature());
        this.email = memberDto.getEmail();
        this.phoneNumber = memberDto.getPhoneNumber();
    }
}
