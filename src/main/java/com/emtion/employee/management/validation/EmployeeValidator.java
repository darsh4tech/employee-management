package com.emtion.employee.management.validation;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.emtion.employee.management.dto.EmployeeDTO;
import com.emtion.employee.management.dto.Response;
import com.emtion.employee.management.entity.Employee;
import com.emtion.employee.management.repository.EmployeeRepository;
import com.emtion.employee.management.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeValidator {

  private EmployeeRepository employeeRepository;

  public EmployeeValidator(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Response<EmployeeDTO> validateAddEmployee(EmployeeDTO employeeDTO) {

    Response<EmployeeDTO> response = new Response<>();
    List<Employee> empList = employeeRepository.findByNameIgnoreCase(employeeDTO.getName().trim());

    if (Utils.isNotEmpty(empList)) {
      response.setHttpStatus(HttpStatus.BAD_REQUEST);
      response.setMsg("Employee name already exist: " + employeeDTO.getName());
      log.info("response: {}", response);
      return response;
    }

    if (employeeDTO.getAge() <= 0) {
      response.setHttpStatus(HttpStatus.BAD_REQUEST);
      response.setMsg("the entered age not valid: " + employeeDTO.getAge());
      return response;
    }

    if (!Utils.isNotEmpty(employeeDTO.getContactList())) {
      response.setHttpStatus(HttpStatus.BAD_REQUEST);
      response.setMsg("this employee has no contact to rech to : " + employeeDTO.getContactList());
      return response;
    }

    response.setHttpStatus(HttpStatus.ACCEPTED);
    return response;

  }

}
