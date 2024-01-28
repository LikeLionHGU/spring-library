package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private feature feature;

    @Column(nullable = false)
    private String phoneNumber;

    public void update(String name, String email, String idNumber, feature feature, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.idNumber = idNumber;
        this.feature = feature;
        this.phoneNumber = phoneNumber;
    }
}
