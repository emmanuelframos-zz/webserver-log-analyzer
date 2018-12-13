package com.webserverloganalyzer.exception;

import com.webserverloganalyzer.api.v1.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> handleBadRequest(MethodArgumentNotValidException ex) {
        logger.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex);
        return ex.getBindingResult().getAllErrors()
                .stream()
                .map(e -> new ErrorDTO(e.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public List<ErrorDTO> handleInternalServerError(Exception ex) {
        logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);
        return Arrays.asList(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
}