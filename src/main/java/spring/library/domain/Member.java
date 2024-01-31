package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long memberId;

	@Column(nullable = false)
	private String name;

	@Column(name = "id_number",nullable = false)
	private int idNumber;

	@Column(nullable = false)
	private String feature;

	@Column(nullable = false)
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;


	@OneToMany(mappedBy = "member")
	public static List<RentalManagement> rentalHistory = new ArrayList<>();


	public static Member toMember(MemberDto memberDto){
		return Member.builder()
			.memberId(memberDto.getMemberId())
			.name(memberDto.getName())
			.idNumber(memberDto.getIdNumber())
			.feature(memberDto.getFeature())
			.email(memberDto.getEmail())
			.phoneNumber(memberDto.getPhoneNumber())
			.build();
	}

	public void update(String name, int idNumber, String feature, String email, String phoneNumber){
		this.name = name;
		this.idNumber = idNumber;
		this.feature = feature;
		this.email = email;
		this.phoneNumber = phoneNumber;

	}



}


