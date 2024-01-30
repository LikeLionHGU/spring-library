package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Member;
import spring.library.domain.PurchaseRequest;
import spring.library.domain.enums.PurchaseRequestProcess;
import spring.library.dto.PurchaseRequestDto;
import spring.library.exception.BookAlreadyExistException;
import spring.library.exception.IdPresenceException;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import spring.library.repository.PurchaseRequestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseRequestService {

    private final PurchaseRequestRepository purchaseRequestRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public void registerPurchaseRequest(PurchaseRequestDto purchaseRequestDto) {
        Member member = validateMemberPresence(purchaseRequestDto.getMemberId());
        validateBookAlreadyExist(purchaseRequestDto);
        purchaseRequestRepository.save(PurchaseRequest.from(member, purchaseRequestDto));
    }

    private Member validateMemberPresence(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IdPresenceException("존재하지 않는 회원입니다."));
    }

    public List<PurchaseRequestDto> getPurchaseRequests(Long memberId) {
        validateMemberPresence(memberId);
        return purchaseRequestRepository.findAllByMemberId(memberId).stream()
                .map(PurchaseRequestDto::from)
                .toList();
    }

    public void validateBookAlreadyExist(PurchaseRequestDto purchaseRequestDto){
        if(bookRepository.existsByTitleAndAuthorAndPublisherAndPublicationYear(purchaseRequestDto.getTitle(),
                purchaseRequestDto.getAuthor(), purchaseRequestDto.getPublisher(), purchaseRequestDto.getPublicationYear()))
            throw new BookAlreadyExistException();
    }

    public void updatePurchaseRequest(Long purchaseRequestId, PurchaseRequestDto purchaseRequestDto) {
        PurchaseRequest purchaseRequest = validatePurchaseRequestPresence(purchaseRequestId);
        purchaseRequest.updateProcessResult(purchaseRequestDto.getProcessResult());
    }

    private PurchaseRequest validatePurchaseRequestPresence (Long purchaseRequestId){
        return purchaseRequestRepository.findById(purchaseRequestId)
                .orElseThrow(() -> new IdPresenceException("존재하지 않는 구매 요청입니다."));
    }

}
