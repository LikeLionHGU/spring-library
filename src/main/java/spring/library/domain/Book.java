package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.dto.BookDto;
import spring.library.dto.RentalManagementDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;


	public void update(String title, String author, String publisher, int publicationYear, String classification){
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.classification = classification;
	}

	public static Book toBookByBookDto(BookDto bookDto){
    return spring.library.domain.Book.builder()
        .title(bookDto.getTitle())
        .author(bookDto.getAuthor())
        .publisher(bookDto.getPublisher())
        .publicationYear(bookDto.getPublicationYear())
        .classification(bookDto.getClassification())
	    .bookId(bookDto.getBookId())
        .build();
	}

	public static List<Book> toBookByMember(Member member, List<BookDto> list_BookDto){
		List<Book> books = new ArrayList<>();
		for(BookDto bookDto : list_BookDto) {
			books.add(spring.library.domain.Book.builder()
				.title(bookDto.getTitle())
				.author(bookDto.getAuthor())
				.publisher(bookDto.getPublisher())
				.publicationYear(bookDto.getPublicationYear())
				.classification(bookDto.getClassification())
				.bookId(bookDto.getBookId())
				.build());
		}
		return books;
	}

}
