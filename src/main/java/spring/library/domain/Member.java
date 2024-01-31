package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.MemberRequest;
import spring.library.dto.MemberDto;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String idNumber;

    @Column(nullable = false)
    private Feature feature;

    @Column(nullable = false)
    private String phoneNumber;

    public void update(MemberDto memberDto) {
        this.name = memberDto.getName();
        this.email = memberDto.getEmail();
        this.idNumber = memberDto.getIdNumber();
        this.feature = Feature.from(memberDto.getFeature());
        this.phoneNumber = memberDto.getPhoneNumber();
    }

    public static Member from(MemberDto memberDto) {
        return Member.builder()
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .idNumber(memberDto.getIdNumber())
                .feature(Feature.from(memberDto.getFeature()))
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}
