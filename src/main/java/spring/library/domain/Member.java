package spring.library.domain;

import spring.library.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long idNumber;

    @Column(nullable = false)
    private String feature;

    @Column(nullable = false)
    private String phoneNumber;

    private int bookCount;

    public static Member toMember(MemberDto memberDto) {
        return Member.builder()
                .name(memberDto.getName())
                .idNumber(memberDto.getIdNumber())
                .feature(memberDto.getFeature())
                .phoneNumber(memberDto.getPhoneNumber())
                .bookCount(memberDto.getBookCount())
                .build();
    }
    public void update(MemberDto memberDto) {
        this.name = memberDto.getName();
        this.idNumber = memberDto.getIdNumber();
        this.feature = memberDto.getFeature();
    }


}
