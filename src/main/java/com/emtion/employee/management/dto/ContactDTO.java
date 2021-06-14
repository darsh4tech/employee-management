package com.emtion.employee.management.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {

  @NotEmpty
  @Size(min = 3, max = 100)
  private String address;
  @NotEmpty
  @Pattern(regexp = "^\\01(\\d){9}$")
  private String mobile;

  private EmployeeDTO employee;
}
