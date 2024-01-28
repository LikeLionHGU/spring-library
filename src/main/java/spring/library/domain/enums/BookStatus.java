package spring.library.domain.enums;

import lombok.Getter;
import spring.library.exception.InvalidEnumValueException;

@Getter
public enum BookStatus {
    AVAILABLE("대출가능"),
    UNAVAILABLE("대출중");

    private final String status;

    BookStatus(String status){
        this.status = status;
    }

    public static BookStatus from(String status) {
        for (BookStatus bookStatus : BookStatus.values()) {
            if (bookStatus.getStatus().equals(status)) {
                return bookStatus;
            }
        }
        throw new InvalidEnumValueException("존재하지 않는 도서 상태입니다.");
    }
}
