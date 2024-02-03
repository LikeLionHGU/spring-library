package spring.library.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.Book;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
	private String title;

	private String author;

	private String publisher;

	private int publicationYear;

	private String classification;

	private Long bookId;


	public BookDto(Book book){
		title = book.getTitle();
		author = book.getAuthor();
		publisher = book.getPublisher();
		publicationYear = book.getPublicationYear();
		classification = book.getClassification();
		bookId = book.getBookId();
	}


	public static BookDto from(Book book){
		return BookDto.builder()
			.title(book.getTitle())
			.author(book.getAuthor())
			.publisher(book.getPublisher())
			.publicationYear(book.getPublicationYear())
			.classification(book.getClassification())
			.bookId(book.getBookId())
			.build();
	}

}
