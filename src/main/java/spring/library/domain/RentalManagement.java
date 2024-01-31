package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RentalManagement {

	@Id
	@Column(name = "check_out_id",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long checkOutId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	@Column(nullable = false)
	private String checkOutDate;

	@Column(nullable = false)
	private String dueDate;

	@Column(nullable = false)
	private int renewalCount;

	@Column(nullable = false)
	private boolean isReturned;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private String status;

	private Long rentMemberId;


	public RentalManagement(Book book, String checkOutDate, String dueDate, Long rentMeberId){
	this.checkOutId = book.getBookId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
		this.renewalCount = 0;
		this.isReturned = true;
		this.status = "대출 중";
		this.rentMemberId = rentMeberId;
	}


	public static RentalManagement BookToRentalManagement(Book book, int borrowRangeday, Long rentMemberId){

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime dueDateTime = LocalDateTime.now().plusDays(borrowRangeday);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String borrowDateFormattedTime = currentTime.format(formatter);
		String dueDateFormattedTime = dueDateTime.format(formatter);

		RentalManagement rentalManagement = new RentalManagement(book, borrowDateFormattedTime, dueDateFormattedTime, rentMemberId);
		return rentalManagement;
	}

}
