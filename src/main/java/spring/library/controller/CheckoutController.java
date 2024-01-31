package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.form.MemberForm;
import spring.library.controller.response.*;
import spring.library.domain.Book;
import spring.library.domain.History;
import spring.library.domain.Member;
import spring.library.dto.BookDto;
import spring.library.dto.MemberDto;
import spring.library.service.BookService;
import spring.library.service.CheckoutService;
import spring.library.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/checkouts")
@RequiredArgsConstructor
@CrossOrigin
public class CheckoutController {

  private final MemberService memberService;
  private final BookService bookService;
  private final CheckoutService checkoutService;

  @PostMapping("/{bookId}")
  public ResponseEntity<ApiResponse> borrowBook(
      @PathVariable Long bookId, @RequestBody MemberForm memberForm) {
    BookDto bookDto = bookService.getBook(bookId);
    MemberDto memberDto = memberService.getMemberInfo(memberForm.getMemberId());
    checkoutService.checkOut(memberDto, bookDto);
    ApiResponse response = new CheckoutBorrowResponse();
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<ApiResponse> getTakenBook(@RequestParam("memberId") Long memberId) {
    List<BookDto> bookDto = checkoutService.getBookTakenByMember(memberId);
    ApiResponse response = new CheckoutListResponse(bookDto);
    return ResponseEntity.ok(response);
  }


  @PatchMapping("/{checkOutId}/return")
  public ResponseEntity<ApiResponse> returnBook(@PathVariable Long checkOutId, @RequestBody MemberForm memberForm){
    BookDto bookDto = bookService.getBook(checkOutId);
    MemberDto memberDto =memberService.getMemberInfo(memberForm.getMemberId());
    checkoutService.returnBook(memberDto,bookDto);
    ApiResponse response = new ReturnResponse();
    return  ResponseEntity.ok(response);
  }


  @GetMapping("/history")
  public ResponseEntity<ApiResponse> historyBook(@RequestParam("memberId") Long memberId){
    List<BookDto> bookDto = checkoutService.getBookHistory(memberId);
    ApiResponse response = new  HistoryListResponse(bookDto);
    return ResponseEntity.ok(response);
  }

}

