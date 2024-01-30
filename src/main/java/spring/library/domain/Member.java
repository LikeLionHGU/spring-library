package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.dto.MemberDto;

import java.util.ArrayList;

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

//	@Column(nullable = false)
//	private String feature;     // 학생 , 교직원

	@Column(nullable = false)
	private String feature;     // 학생 , 교직원

	@Column(nullable = false)
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	// todo : 회원이 책 대출하는 거
//
//	@Column(name = "rentalHistory")
//	public ArrayList<Book> rentalHistory = new ArrayList<>();

	// 여기까지


	public static Member toOrdinary(MemberDto ordinaryDto){
		return Member.builder()
			.memberId(ordinaryDto.getMemberId())
			.name(ordinaryDto.getName())
			.idNumber(ordinaryDto.getIdNumber())
			.feature(ordinaryDto.getFeature())
			.email(ordinaryDto.getEmail())
			.phoneNumber(ordinaryDto.getPhoneNumber())
			.build();
	}

//	public void update(Long id, MemberDto ordinaryDto){
//		memberId = id;
//		name = ordinaryDto.getName();
//		feature = ordinaryDto.getFeature();
//		idNumber = ordinaryDto.getIdNumber();
//		email = ordinaryDto.getEmail();
//		phoneNumber = ordinaryDto.getPhoneNumber();
//	}
// 위의 것은 왜 작동을 제대로 하지 않는지?

	public void update(String name, int idNumber, String feature, String email, String phoneNumber){
		this.name = name;
		this.idNumber = idNumber;
		this.feature = feature;
		this.email = email;
		this.phoneNumber = phoneNumber;

	}

/*
회원은 대출중이 아닌 도서를 대출할 수 있다.
학생은 최대 10권, 권당 10일 대출
교직원은 최대 20권, 권당 30일 대출
관리자는 최대 100권, 권당 110813일 대출
회원은 대출한 도서 목록을 조회할 수 있다.
회원은 대출중인 도서를 반납할 수 있다.
회원은 대출한 도서의 반납 기한을 연장할 수 있다.
연장은 1회에 한해 허용되며, 반납 예정일 당일에만 가능하다.
연장시 반납 예정일은 5일 추가된다.
*/

}


