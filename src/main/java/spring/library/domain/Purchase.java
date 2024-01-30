package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.request.PurchaseRequest;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long purchaseId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false)
    private int publicationYear;
    @Column(nullable = false)
    private String requestDate;
    @Column(nullable = false)
    private String dateOfProcess;
    @Column(nullable = false)
    private ProcessResult processResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public static Purchase toPurchase(Member member, PurchaseRequest purchaseRequest) {
        return Purchase.builder()
                .member(member)
                .title(purchaseRequest.getTitle())
                .author(purchaseRequest.getAuthor())
                .publisher(purchaseRequest.getPublisher())
                .publicationYear(purchaseRequest.getPublicationYear())
                .requestDate(LocalDate.now().toString())
                .dateOfProcess("")
                .processResult(ProcessResult.신청)
                .build();
    }

    public void update(String dateOfProcess, ProcessResult processResult) {
        this.dateOfProcess = dateOfProcess;
        this.processResult = processResult;
    }
}
