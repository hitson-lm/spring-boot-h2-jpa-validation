package com.example.h2.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {

  @NotBlank
  @Size(min = 2, max = 25)
  private String name;

  @NotBlank
  @Size(min = 9, max = 11)
  private String phone;

}
