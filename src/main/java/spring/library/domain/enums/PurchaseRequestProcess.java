package spring.library.domain.enums;

import lombok.Getter;

@Getter
public enum PurchaseRequestProcess {
    REQUEST("신청"),
    APPROVE("승인"),
    REJECT("거절");

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
        return REQUEST;
    }
}
