package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.exception.BookIsAlreadyReturnedException;
import spring.library.exception.CheckoutRenewalCountLimitException;
import spring.library.exception.NotRenewalPeriodException;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Checkout extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkoutListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "renewal_count", nullable = false)
    private int renewalCount;

    @Column(name = "is_returned", nullable = false)
    private boolean isReturned;

    public static Checkout from(Member member, Book book){
        return Checkout.builder()
                .member(member)
                .book(book)
                .loanDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(member.getFeature().getBorrowLimit()))
                .renewalCount(0)
                .isReturned(false)
                .build();
    }

    public void returnBook() {
        this.isReturned = true;
    }

    public void validateIsReturned() {
        if (this.isReturned) {
            throw new BookIsAlreadyReturnedException();
        }
    }

    public void extendCheckout(int extensionDay) {
        this.dueDate = this.dueDate.plusDays(extensionDay);
        this.renewalCount++;
    }

    public void validateRenewalCount(int renewalCount) {
        if (this.renewalCount >= renewalCount) {
            throw new CheckoutRenewalCountLimitException();
        }
    }

    public void validateRenewalPeriod(int renewalDateBeforeDueDate) {
        if (LocalDate.now().isBefore(this.dueDate.minusDays(renewalDateBeforeDueDate))||LocalDate.now().isAfter(this.dueDate)) {
            throw new NotRenewalPeriodException();
        }
    }
}
