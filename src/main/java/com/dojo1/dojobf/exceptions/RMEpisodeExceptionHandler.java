package com.dojo1.dojobf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RMEpisodeExceptionHandler {

    @ExceptionHandler(value = RMEpisodeException.class)
    ResponseEntity<RMEpisodeErrorInfo> rmEpisodeExceptionHandler(RMEpisodeException e) {
        HttpStatus httpStatus = HttpStatus.MULTI_STATUS;

        if (RMEpisodeError.EPISODE_NOT_FOUND_ERROR.equals(e.getRmEpisodeError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus).body(new RMEpisodeErrorInfo(e.getRmEpisodeError().getMessage()));
    }
}