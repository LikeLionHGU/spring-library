package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.domain.ProcessResult;
import spring.library.domain.Purchase;
import spring.library.controller.request.PurchaseRequest;
import spring.library.controller.response.purchase.PurchaseListResponse;
import spring.library.controller.response.purchase.PurchaseResponse;
import spring.library.dto.PurchaseDto;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import spring.library.repository.PurchaseRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;

    public Long createPurchase(PurchaseDto purchaseDto){
        Member member = memberRepository.findById(purchaseDto.getMemberId()).orElseThrow(() -> new MemberNotFoundException());
        Book existBook = bookRepository.findByTitleAndAuthorAndPublisherAndPublicationYear(purchaseDto.getTitle(), purchaseDto.getAuthor(), purchaseDto.getPublisher(), purchaseDto.getPublicationYear());
        // 같은 책이라도 신청하는 사람이 다르면 구매요청이 중복될 수 있음 (나중에 중복되는 책 우선 구매 등 고려)
        Purchase existPurchase = purchaseRepository.findByMemberAndTitleAndAuthorAndPublisherAndPublicationYear(member, purchaseDto.getTitle(), purchaseDto.getAuthor(), purchaseDto.getPublisher(), purchaseDto.getPublicationYear());

        if(existBook != null) {
            throw new RuntimeException("이미 존재하는 책입니다.");
        }
        if(existPurchase != null) {
            throw new RuntimeException("이미 존재하는 구매요청입니다.");
        }

        Purchase purchase = Purchase.from(purchaseDto, member);
        purchaseRepository.save(purchase);
        return purchase.getPurchaseId();
    }
    public PurchaseListResponse getPurchaseList(PurchaseDto purchaseDto) {
        Member member = memberRepository.findById(purchaseDto.getMemberId()).orElseThrow(() -> new MemberNotFoundException());
        String feature = member.getFeature().getFeature();
        List<PurchaseResponse> purchaseResponses = new ArrayList<>();
        if(feature.equals("학생") || feature.equals("교직원")) {
            List<Purchase> purchases = purchaseRepository.findByMember_MemberId(member.getMemberId());
            for(Purchase purchase : purchases) {
                purchaseResponses.add(new PurchaseResponse(purchase));
            }
        }
        else{
            List<Purchase> purchases = purchaseRepository.findAll();
            for(Purchase purchase : purchases) {
                purchaseResponses.add(new PurchaseResponse(purchase));
            }
        }
        return new PurchaseListResponse(purchaseResponses);
    }
    public void updatePurchase(Long purchaseId, PurchaseDto purchaseDto) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new RuntimeException("존재하지 않는 구매요청입니다."));
        purchase.update(purchaseDto.getDateOfProcess(), purchaseDto.getProcessResult());
        purchaseRepository.save(purchase);
    }
}
