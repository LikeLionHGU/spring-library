package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.domain.enums.PurchaseRequestProcess;
import spring.library.dto.PurchaseRequestDto;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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
    private LocalDateTime dateOfProcess;

    @Column(name = "process_result")
    @Enumerated(EnumType.STRING)
    private PurchaseRequestProcess processResult;

    public static PurchaseRequest from(Member member, PurchaseRequestDto purchaseRequestDto){
        return PurchaseRequest.builder()
                .member(member)
                .title(purchaseRequestDto.getTitle())
                .author(purchaseRequestDto.getAuthor())
                .publisher(purchaseRequestDto.getPublisher())
                .publicationYear(purchaseRequestDto.getPublicationYear())
                .build();
    }
}
