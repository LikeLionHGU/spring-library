package spring.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.library.controller.response.ExceptionResponse;
import spring.library.exception.*;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InvalidEnumValueException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidEnumValueException(InvalidEnumValueException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IdNumberAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleIdNumberAlreadyExistsException(IdNumberAlreadyExistsException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(IdPresenceException.class)
    public ResponseEntity<ExceptionResponse> handleMemberIdPresenceException(IdPresenceException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BookIsUnavailableException.class)
    public ResponseEntity<ExceptionResponse> handleBookIsUnavailableException(BookIsUnavailableException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BookIsAlreadyReturnedException.class)
    public ResponseEntity<ExceptionResponse> handleCheckoutHistoryListResponse(BookIsAlreadyReturnedException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(CheckoutRenewalCountLimitException.class)
    public ResponseEntity<ExceptionResponse> handleCheckoutRenewalCountLimitException(CheckoutRenewalCountLimitException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NotRenewalPeriodException.class)
    public ResponseEntity<ExceptionResponse> handleNotRenewalPeriodException(NotRenewalPeriodException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
