package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.request.MemberRequest;

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

    public void update(MemberRequest memberRequest) {
        this.name = memberRequest.getName();
        this.email = memberRequest.getEmail();
        this.idNumber = memberRequest.getIdNumber();
        this.feature = memberRequest.getFeature();
        this.phoneNumber = memberRequest.getPhoneNumber();
    }

    public static Member toMember(MemberRequest memberRequest) {
        return Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .idNumber(memberRequest.getIdNumber())
                .feature(memberRequest.getFeature())
                .phoneNumber(memberRequest.getPhoneNumber())
                .build();
    }
}
