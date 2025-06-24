package com.example.h2.exceptions;

import com.example.h2.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestControllerAdvice
public class RestGlobalExceptionAdvice extends ResponseEntityExceptionHandler {

  // metodo para capturar las validacion con anotaciones de Jakarta
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatusCode status,
                                                                WebRequest request) {

    List<ExceptionDetails> fieldErrors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> new ExceptionDetails(
            PropertyUtils.getApplicationCode(),
            error.getField() + " " + error.getDefaultMessage())
        )
        .collect(Collectors.toList());

    //.map(fieldError -> fieldError.getField() + ", " + fieldError.getDefaultMessage())
        //.toList();

    System.out.println("Headers: " + headers);
    System.out.println("Local Context Sprin boot: " + LocaleContextHolder.getLocale());
    log.info("list errors: "+ fieldErrors);

    ApiError error = ApiError.builder()
        .code("TL003")
        .description("Los datos proporcionados son incorrectos.")
        //.description(String.join(" || ", fieldErrors))
        .exceptionDetails(fieldErrors)
        .build();

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ExceptionNotFoundByIdUser.class)
  public ResponseEntity<ApiError> exceptionApi(ExceptionNotFoundByIdUser ex) {

    ApiError error = ApiError.builder()
        .code("TL001")
        .description(ex.getMessage())
        .build();

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  // metodo para capturar las validacion sin anotaciones.
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiError> exceptionCreate(IllegalArgumentException ex) {

    ApiError error = ApiError.builder()
        .code("TL002")
        .description(ex.getMessage())
        .build();

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }



}
