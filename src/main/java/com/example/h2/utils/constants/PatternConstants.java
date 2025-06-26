package com.example.h2.utils.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE) //Restringe la creación de instancias.
public class PatternConstants {

  public static final String REG_EXP_VALID_ID = "^(100|[1-9][0-9]?)$";

}
