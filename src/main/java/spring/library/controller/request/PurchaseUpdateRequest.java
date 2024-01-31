package spring.library.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseUpdateRequest {
    private String dateOfProcess;
    private String processResult;
}
