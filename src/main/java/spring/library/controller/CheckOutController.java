package spring.library.controller;

import lombok.RequiredArgsConstructor;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.CheckOutForm;
import spring.library.controller.response.CheckOutBookResponse;
import spring.library.controller.response.CheckOutHistoryListResponse;
import spring.library.dto.CheckOutDto;
import spring.library.service.CheckOutService;

@RestController
@RequestMapping("/checkouts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CheckOutController {
    private final CheckOutService checkOutService;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;







}
