package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Book;
import spring.library.domain.Member;

import java.util.ArrayList;

//@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

	private Long memberId;
	private String name;
	private int idNumber;
	private String feature;     // 관리자, 학생 , 교직원
	private String email;
	private String phoneNumber;

	// todo : 대여 기능 구현

	public ArrayList<Book> rentalHistory = new ArrayList<>();

	// 여기


	public static MemberDto from(Member ordinary){
    return MemberDto.builder()
        .memberId(ordinary.getMemberId())
        .name(ordinary.getName())
        .idNumber(ordinary.getIdNumber())
        .feature(ordinary.getFeature())
        .email(ordinary.getEmail())
        .phoneNumber(ordinary.getPhoneNumber())
        .build();
	}

	public MemberDto(Member member){
		memberId = member.getMemberId();
		name = member.getName();
		idNumber = member.getIdNumber();
		feature = member.getFeature();
		email = member.getEmail();
		phoneNumber = member.getPhoneNumber();
	}

}
