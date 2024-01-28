package spring.library.controller.response;

import spring.library.dto.BookDto;

public class BookResponse {


    private String title;

    private String content;
    private String author;
    private String publisher;
    private int publicationYear;
    private String classification;
    private String status;
    private int amount;


    public BookResponse(BookDto bookDto) {
        this.title =bookDto.getTitle();
        this.content = bookDto.getContent();
        this.author = bookDto.getAuthor();
        this.publisher = bookDto.getPublisher();
        this.publicationYear = bookDto.getPublicationYear();
        this.classification = bookDto.getClassification();
        this.status = bookDto.getStatus();
        this.amount = bookDto.getAmount();
    }

}
