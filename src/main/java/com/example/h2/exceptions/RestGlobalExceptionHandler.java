/*
package com.example.h2.exceptions;

import com.example.h2.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * Clase para captura errores generales o globales que no son manejadas por la aplicación.
 * Cualquier tipo exception que se produzca, esta clase lo estará capturando, no importa el tipo
 *
 *//*

@Slf4j
@RestControllerAdvice
public class RestGlobalExceptionHandler {

  // metodo para capturar las validacion con anotaciones de Jakarta @Valid

  */
/**
   * Metodo que capturar errores de tipo MethodArgumentNotValidException detecta la validacion con
   * anotaciones de Jakarta @Valid Ejemplo: cuando se crea un usuario en el metodo POST
   * @param ex
   * @return
   *//*

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

    List<ExceptionDetails> fieldErrors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> new ExceptionDetails(
            PropertyUtils.getApplicationCode(),
            error.getField() + " " + error.getDefaultMessage())
        )
        .collect(Collectors.toList());

    //.map(fieldError -> fieldError.getField() + ", " + fieldError.getDefaultMessage())
    //.toList();

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

  */
/**
   * Esta exception es para cuando hay un error en el sistema.
   * ejemplo: la BD esta apagada o no hay conexion en algun servicio.
   *//*

  */
/*@ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception ex) {

    List<ExceptionDetails> listDetail = new ArrayList<>();
    listDetail.add(ExceptionDetails.builder()
        .component(PropertyUtils.getApplicationCode())
        .message("Se ha producido un error. Por favor contacte al administrador o intente mas tarde")
        .build());

    ApiError error = ApiError.builder()
        .code("TL500")
        .description("Error en el servidor.")
        //.description(String.join(" || ", fieldErrors))
        .exceptionDetails(listDetail)
        .build();

    return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
  }*//*



  // metodo para capturar las validacion sin anotaciones.
  */
/*@ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiError> exceptionCreate(IllegalArgumentException ex) {

    List<ExceptionDetails> listDetail = new ArrayList<>();

    listDetail.add(ExceptionDetails.builder()
        .component(PropertyUtils.getApplicationCode())
        .message(ex.getMessage())
        .build());

    ApiError error = ApiError.builder()
        .code("TL002")
        .description("Los datos proporcionados son incorrectos.")
        .exceptionDetails(listDetail)
        .build();
    log.info("IllegalArgumentException: {}", ex.toString());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }*//*




}
*/
