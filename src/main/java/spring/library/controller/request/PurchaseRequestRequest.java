package spring.library.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseRequestRequest {
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
}
