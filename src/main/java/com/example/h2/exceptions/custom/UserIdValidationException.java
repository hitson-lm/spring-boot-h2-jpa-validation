package com.example.h2.exceptions.custom;

public class UserIdValidationException extends RuntimeException{

  public UserIdValidationException(String message) {
    super(message);

  }
}
