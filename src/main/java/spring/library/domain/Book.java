package spring.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import spring.library.dto.BookDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Book {
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	@Column(nullable = false)
	private String publisher;

	@Column(nullable = false)
	private int publicationYear;

	@Column(nullable = false)
	private String classification;

	// todo: 대출 관련
	@Column
	private String checkOutDate;

	@Column
	private String dueDate;

	@Column
	private int renewalCount;

	// 여기까지


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member oridinary;


	public void update(String title, String author, String publisher, int publicationYear, String classification){
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.classification = classification;
	}


	public static Book toBookByBookDto(BookDto bookDto){      // -> 이것일 경우, method 넣는 걸로.
    return Book.builder()
        .title(bookDto.getTitle())
        .author(bookDto.getAuthor())
        .publisher(bookDto.getPublisher())
        .publicationYear(bookDto.getPublicationYear())
        .classification(bookDto.getClassification())
//        .status(bookDto.getStatus())
	    .bookId(bookDto.getBookId())
        .build();
	}

	public static Book toBookByOrdinary(BookDto bookDto, Member ordinary){
		return Book.builder()
			.title(bookDto.getTitle())
			.author(bookDto.getAuthor())
			.publisher(bookDto.getPublisher())
			.publicationYear(bookDto.getPublicationYear())
			.classification(bookDto.getClassification())
//			.status(bookDto.getStatus())
			.bookId(bookDto.getBookId())
			.oridinary(ordinary)
			.build();
	}
}
