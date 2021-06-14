package com.emtion.employee.management.dto;

import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {

  private HttpStatus httpStatus;
  private String msg;
  private T data;


}
