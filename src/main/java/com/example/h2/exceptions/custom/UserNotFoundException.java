package com.example.h2.exceptions.custom;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String message) {
    super(message);

  }

}
