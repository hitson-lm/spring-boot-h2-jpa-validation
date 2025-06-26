package com.example.h2.exceptions.custom;

import com.example.h2.exceptions.ApiError;
import com.example.h2.exceptions.ExceptionDetails;
import com.example.h2.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para captura errores manejadas, excepciones propias de la aplicación.
 *
*/
@RestControllerAdvice
//@Primary
@Slf4j
public class RestCustomExceptionHandler extends RuntimeException {

/**
   * Metodo que capturar errores manejadas de tipo UserNotFoundException
   * Example: cuando usuario no existe en la BD.
   * @param ex
   * @return
 */
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handle(UserNotFoundException ex) {

    List<ExceptionDetails> listDetail = new ArrayList<>();

    listDetail.add(ExceptionDetails.builder()
        .component(PropertyUtils.getApplicationCode())
        .message(ex.getMessage())
        .build());

    ApiError error = ApiError.builder()
        .code("TL005")
        .description("Los datos proporcionados son incorrectos.")
        //.description(String.join(" || ", fieldErrors))
        .exceptionDetails(listDetail)
        .build();

    log.error("Exception de tipo 'UserNotFoundException': {}",error.toString());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(UserIdValidationException.class)
  public ResponseEntity<Object> handlException(UserIdValidationException ex) {

    List<ExceptionDetails> listDetail = new ArrayList<>();

    listDetail.add(ExceptionDetails.builder()
        .component(PropertyUtils.getApplicationCode())
        .message(ex.getMessage())
        .build());

    ApiError error = ApiError.builder()
        .code("TL006")
        .description("Los datos proporcionados son incorrectos.")
        //.description(String.join(" || ", fieldErrors))
        .exceptionDetails(listDetail)
        .build();

    log.error("ERROR: {}",error.toString());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

  }


}
