package com.emtion.employee.management.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeDTO {

  @NotEmpty
  @Size(min = 3, max = 25)
  private String name;
  private Integer age;
  private EmployeeStates states;
  @NotEmpty
  private List<ContactDTO> contactList;

}
