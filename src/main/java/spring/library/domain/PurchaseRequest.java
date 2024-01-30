package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.domain.enums.PurchaseRequestProcess;
import spring.library.dto.PurchaseRequestDto;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseRequest extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_request_id")
    private Long purchaseRequestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "author", length = 30, nullable = false)
    private String author;

    @Column(name = "publisher", length = 30, nullable = false)
    private String publisher;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "date_of_process")
    private LocalDate dateOfProcess;

    @Column(name = "process_result")
    @Enumerated(EnumType.STRING)
    private PurchaseRequestProcess processResult;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    public static PurchaseRequest from(Member member, PurchaseRequestDto purchaseRequestDto){
        return PurchaseRequest.builder()
                .member(member)
                .title(purchaseRequestDto.getTitle())
                .author(purchaseRequestDto.getAuthor())
                .publisher(purchaseRequestDto.getPublisher())
                .publicationYear(purchaseRequestDto.getPublicationYear())
                .requestDate(LocalDate.now())
                .processResult(PurchaseRequestProcess.REQUEST)
                .build();
    }

    public void updateProcessResult(String processResult) {
        this.processResult = PurchaseRequestProcess.from(processResult);
        this.dateOfProcess = LocalDate.now();
    }
}
