package spring.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.CheckoutDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkoutId;

    @Column(nullable = false)
    private String checkOutDate;

    @Column(nullable = false)
    private String dueDate;

    @Column(nullable = false)
    private int renewalCount;

    @Column(nullable = false)
    private boolean isReturned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book;

    public static Checkout from(Book book, Member member) {
        return Checkout.builder()
                .book(book)
                .member(member)
                .checkOutDate(String.valueOf(LocalDate.now()))
                .dueDate(String.valueOf(LocalDate.now().plusDays(member.getFeature().getBorrowLimit())))
                .isReturned(false)
                .renewalCount(0)
                .build();
    }

    public void updateIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public void updateRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;

        LocalDate due = LocalDate.parse(this.dueDate, DateTimeFormatter.ISO_LOCAL_DATE);
        due = due.plusDays(5);
        this.dueDate = due.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
