package spring.library.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {
    private Long bookId;
    private Long memberId;
    private String loanDate;
    private String dueDate;
    private int renewalCount;
    private boolean isReturned;
}
