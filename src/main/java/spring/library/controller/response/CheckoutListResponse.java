package spring.library.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.CheckoutDto;

import java.time.LocalDate;
import java.util.List;

@Getter
public class CheckoutListResponse {
    private final List<Book> books;

    public CheckoutListResponse(List<CheckoutDto> checkoutDtos) {
        this.books = checkoutDtos.stream()
                .map(Book::from)
                .toList();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Book {
        private Long checkOutId;
        private String title;
        private String author;
        private LocalDate checkOutDate;
        private LocalDate dueDate;
        private int renewalCount;

        public static Book from(CheckoutDto checkoutDto) {
            return Book.builder()
                    .checkOutId(checkoutDto.getCheckOutId())
                    .title(checkoutDto.getTitle())
                    .author(checkoutDto.getAuthor())
                    .checkOutDate(checkoutDto.getCheckOutDate())
                    .dueDate(checkoutDto.getDueDate())
                    .renewalCount(checkoutDto.getRenewalCount())
                    .build();
        }
    }
}
