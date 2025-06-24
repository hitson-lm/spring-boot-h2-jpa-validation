package com.example.h2.utils;

import com.example.h2.exceptions.ApiError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExceptionUtil {

  public void throwRequestError(List<String> msgErrorList) {

    if(!msgErrorList.isEmpty()) {

      msgErrorList.forEach(msg -> {
        var builder = ApiError.builder()
            .code("43")
            .description(msg)
            .build();
          });
    }
  }



}
