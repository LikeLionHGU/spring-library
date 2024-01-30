package spring.library.domain.enums;

import lombok.Getter;
import spring.library.exception.InvalidEnumValueException;

@Getter
public enum PurchaseRequestProcess {
    REQUEST("신청"),
    APPROVE("승인"),
    CANCEL("취소");

    private final String process;

    PurchaseRequestProcess(String process){
        this.process = process;
    }
    public static PurchaseRequestProcess from(String process) {
        for (PurchaseRequestProcess purchaseRequestProcess : PurchaseRequestProcess.values()) {
            if (purchaseRequestProcess.getProcess().equals(process)) {
                return purchaseRequestProcess;
            }
        }
        throw new InvalidEnumValueException("유효하지 않은 구매 요청 처리 결과입니다.");
    }
}
