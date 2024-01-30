package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.controller.form.PurchaseUpdateForm;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.domain.ProcessResult;
import spring.library.domain.Purchase;
import spring.library.dto.request.PurchaseRequest;
import spring.library.dto.response.purchase.PurchaseListResponse;
import spring.library.dto.response.purchase.PurchaseResponse;
import spring.library.exception.MemberNotFoundException;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import spring.library.repository.PurchaseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PurchaseRepository purchaseRepository;

    public Long createPurchase(PurchaseRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberNotFoundException());
        Book existBook = bookRepository.findByTitleAndAuthorAndPublisherAndPublicationYear(request.getTitle(), request.getAuthor(), request.getPublisher(), request.getPublicationYear());
        Purchase existPurchase = purchaseRepository.findByMemberAndTitleAndAuthorAndPublisherAndPublicationYear(member, request.getTitle(), request.getAuthor(), request.getPublisher(), request.getPublicationYear());
        if(existBook != null) {
            throw new RuntimeException("이미 존재하는 책입니다.");
        }
        if(existPurchase != null) {
            throw new RuntimeException("이미 존재하는 구매요청입니다.");
        }
        Purchase purchase = Purchase.builder()
                .member(member)
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .publicationYear(request.getPublicationYear())
                .processResult(ProcessResult.신청)
                .dateOfProcess("")
                .requestDate(String.valueOf(LocalDate.now()))
                .build();
        purchaseRepository.save(purchase);
        return purchase.getPurchaseId();
    }
    public PurchaseListResponse getPurchaseList(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        String feature = String.valueOf(member.getFeature());
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
//        List<Purchase> purchases = purchaseRepository.findByMember_MemberId(member.getMemberId())

//        for(Purchase purchase : purchases) {
//            purchaseResponses.add(new PurchaseResponse(purchase));
//        }
        return new PurchaseListResponse(purchaseResponses);
    }

    public void updatePurchase(Long purchaseId, PurchaseUpdateForm form) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new RuntimeException("존재하지 않는 구매요청입니다."));
        purchase.update(form.getDateOfProcess(), form.getProcessResult());
        purchaseRepository.save(purchase);
    }
}
