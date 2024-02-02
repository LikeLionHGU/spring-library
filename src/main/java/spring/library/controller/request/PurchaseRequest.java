package spring.library.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.ProcessResult;

@Getter
@NoArgsConstructor
public class PurchaseRequest {
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
}
