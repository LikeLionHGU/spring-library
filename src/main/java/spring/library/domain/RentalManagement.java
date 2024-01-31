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


	public RentalManagement(Book book, String checkOutDate, String dueDate){
	this.checkOutId = book.getBookId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
		this.renewalCount = 0;
		this.isReturned = true;
		this.status = "대출완료";
	}



	public static RentalManagement BookToRentalManagement(Book book, int borrowRangeday){

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime dueDateTime = LocalDateTime.now().plusDays(borrowRangeday);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String borrowDateFormattedTime = currentTime.format(formatter);
		String dueDateFormattedTime = dueDateTime.format(formatter);

		RentalManagement rentalManagement = new RentalManagement(book, borrowDateFormattedTime, dueDateFormattedTime);
		return rentalManagement;
	}

}
