package spring.library.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.ProcessResult;

@Getter
@NoArgsConstructor
public class PurchaseUpdateForm {
    private ProcessResult processResult;
    private String dateOfProcess;
}
