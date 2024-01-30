package spring.library.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.domain.Member;
import spring.library.domain.PurchaseRequest;
import spring.library.dto.PurchaseRequestDto;
import spring.library.exception.IdPresenceException;
import spring.library.repository.MemberRepository;
import spring.library.repository.PurchaseRequestRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseRequestService {

    private final PurchaseRequestRepository purchaseRequestRepository;
    private final MemberRepository memberRepository;

    public void registerPurchaseRequest(PurchaseRequestDto purchaseRequestDto) {
        Member member = MemberPresence(purchaseRequestDto.getMemberId());
        purchaseRequestRepository.save(PurchaseRequest.from(member, purchaseRequestDto));
    }

    private Member MemberPresence(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IdPresenceException("존재하지 않는 회원입니다."));
    }
}
