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

	@Column(nullable = true)
	private String checkOutDate;

	@Column(nullable = true)
	private String dueDate;

	@Column(nullable = false)
	private int renewalCount;

	@Column(nullable = false)
	private boolean isReturned;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private String status;

	@Column(nullable = true)
	private Long rentMemberId;


	public RentalManagement(Book book, String checkOutDate, String dueDate, int renewalCount , boolean isReturned, String status , Long rentMeberId){
	this.checkOutId = book.getBookId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
		this.renewalCount = renewalCount;
		this.isReturned = isReturned;
		this.status = status;
		this.rentMemberId = rentMeberId;
	}


	public static RentalManagement RentBookToRentalManagement(Book book, int borrowRangeday, Long rentMemberId){

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime dueDateTime = LocalDateTime.now().plusDays(borrowRangeday);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String borrowDateFormattedTime = currentTime.format(formatter);
		String dueDateFormattedTime = dueDateTime.format(formatter);

		RentalManagement rentalManagement = new RentalManagement(book, borrowDateFormattedTime, dueDateFormattedTime, 0, false, "대출 중" ,rentMemberId);
		return rentalManagement;
	}

	public static RentalManagement ReturnBookToRentalManagerment(Book book){
		RentalManagement rentalManagement = new RentalManagement(book, null, null, 0, true, "대출 가능", null);
		return rentalManagement;
	}

}
