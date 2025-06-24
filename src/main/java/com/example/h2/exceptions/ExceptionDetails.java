package com.example.h2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record ExceptionDetails(String component, String message) {

}
/*@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {

  private String component;
  private String description;
}*/
