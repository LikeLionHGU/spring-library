package spring.library.domain;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Builder // @Builder를 사용하면 생성자 대신에 빌더 패턴을 통해 객체를 생성할 수 있습니다.
@Getter// Lombok에서 제공하는 어노테이션으로, 해당 클래스의 모든 필드에 대한 getter 메서드를 자동으로 생성해줍니다.
@NoArgsConstructor /*Lombok에서 제공하는 어노테이션으로, 파라미터 없는 기본 생성자를 자동으로 생성해줍니다.
JPA에서는 기본 생성자가 필요하므로, 엔티티 클래스에서 많이 사용됩니다. */
@AllArgsConstructor /*Lombok에서 제공하는 어노테이션으로, 모든 필드를 인자로 받는 생성자를 자동으로 생성해줍니다.
이 생성자를 사용하면 모든 필드를 포함한 인자를 이용하여 객체를 생성할 수 있습니다. */
public class CheckOut {

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

    public static CheckOut from(Book book, Member member) {
        CheckOut.CheckOutBuilder checkOutBuilder = CheckOut.builder()
                .checkOutDate(String.valueOf(LocalDate.now()))
                .book(book)
                .member(member)
                .isReturned(false)
                .renewalCount(0);

        String job = member.getFeature();
        LocalDate checkOutDate = LocalDate.now();
        int plusDate;

        // 직업에 따라 대여 기간을 설정
        if ("staff".equals(job)) {
            plusDate = 30;
        } else if ("manager".equals(job)) {
            plusDate = 110813;
        } else {
            plusDate = 10;
        }

        // 설정된 대여 기간을 빌더에 추가
        return checkOutBuilder
                .dueDate(String.valueOf(checkOutDate.plusDays(plusDate)))
                .build();
    }

    public void updateRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;

        LocalDate due = LocalDate.parse(this.dueDate, DateTimeFormatter.ISO_LOCAL_DATE);
        due = due.plusDays(5);
        this.dueDate = due.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }


}
