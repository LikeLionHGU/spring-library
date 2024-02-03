package spring.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Book;
import spring.library.domain.Member;

import java.util.ArrayList;
import java.util.List;

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

	public List<Book> rentalHistory;

	public static MemberDto from(Member member){
    return MemberDto.builder()
        .memberId(member.getMemberId())
        .name(member.getName())
        .idNumber(member.getIdNumber())
        .feature(member.getFeature())
        .email(member.getEmail())
        .phoneNumber(member.getPhoneNumber())
//	    .rentalHistory(member.rentalHistory)
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
