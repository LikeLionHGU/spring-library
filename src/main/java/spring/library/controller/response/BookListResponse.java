package spring.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.BookDto;

import java.util.List;

@Getter
public class BookListResponse {
    private final List<Book> books;

    public BookListResponse(List<BookDto> bookDtos){
        this.books = bookDtos.stream()
                .map(Book::from)
                .toList();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Book{
        private String title;
        private String author;
        private String publisher;
        private int publicationYear;
        private String classification;
        private String status;

        public static Book from(BookDto bookDto){
            return Book.builder()
                    .title(bookDto.getTitle())
                    .author(bookDto.getAuthor())
                    .publisher(bookDto.getPublisher())
                    .publicationYear(bookDto.getPublicationYear())
                    .classification(bookDto.getClassification())
                    .status(bookDto.getStatus())
                    .build();
        }
    }
}
