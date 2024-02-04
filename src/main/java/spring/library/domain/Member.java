package spring.library.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.*;
import spring.library.dto.MemberDto;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;
    private String feature;
    private String email;
    private String phoneNumber;
    private int numberBooks;


    public static Member toMember(MemberDto memberDto) {
        return Member.builder()
                .memberId(memberDto.getMemberId())
                .name(memberDto.getName())
                .feature(memberDto.getFeature())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .numberBooks(memberDto.getNumberBooks())
                .build();
        }
    }

