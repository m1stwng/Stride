package dev.m1stwng.stride.common.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    ProblemDetail handleBaseException(BaseException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getDetails());

        problemDetail.setTitle(e.getTitle());

        return problemDetail;
    }
}
