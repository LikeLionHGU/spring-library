package spring.library.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.ProcessResult;

@Getter
@NoArgsConstructor
public class PurchaseForm {
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
//    private String requestDate;
//    private String dateOfProcess;
    private ProcessResult processResult;
}
