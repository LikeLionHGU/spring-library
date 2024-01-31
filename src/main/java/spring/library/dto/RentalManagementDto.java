package spring.library.dto;

import lombok.*;
import spring.library.domain.Book;
import spring.library.domain.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalManagementDto {
	private Long checkOutId;
	private String title;
	private String author;
	private String checkOutDate;
	private String dueDate;
	private int renewalCount;
	private boolean isReturned;

	private Member member;

	private Book book;

	private String status;

//	public RentalManagementDto toRentalManagementDto(Book book){
//
//	}




}
