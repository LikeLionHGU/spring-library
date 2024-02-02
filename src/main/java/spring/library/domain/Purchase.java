package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.controller.request.PurchaseRequest;
import spring.library.dto.PurchaseDto;

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
    public static Purchase from(PurchaseDto purchaseDto, Member member) {
        return Purchase.builder()
                .member(member)
                .title(purchaseDto.getTitle())
                .author(purchaseDto.getAuthor())
                .publisher(purchaseDto.getPublisher())
                .publicationYear(purchaseDto.getPublicationYear())
                .requestDate(LocalDate.now().toString())
                .dateOfProcess("")
                .processResult(ProcessResult.APPLY)
                .build();
    }

    public void update(String dateOfProcess, String processResult) {
        this.dateOfProcess = dateOfProcess;
        this.processResult = ProcessResult.from(processResult);
    }
}
