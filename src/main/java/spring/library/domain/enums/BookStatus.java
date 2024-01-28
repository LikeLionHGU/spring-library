package spring.library.domain.enums;

import lombok.Getter;

@Getter
public enum BookStatus {
    AVAILABLE("대출가능"),
    UNAVAILABLE("대출불가능");

    private final String status;

    BookStatus(String status){
        this.status = status;
    }

    public static BookStatus getBookStatus(String status){
        for(BookStatus bookStatus : BookStatus.values()){
            if(bookStatus.getStatus().equals(status)){
                return bookStatus;
            }
        }
        return null;
    }
}
