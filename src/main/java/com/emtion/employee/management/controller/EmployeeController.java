package com.emtion.employee.management.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emtion.employee.management.dto.EmployeeDTO;
import com.emtion.employee.management.dto.Response;
import com.emtion.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping
  public Response<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
    return employeeService.addEmployee(employeeDTO);
  }

  @PutMapping("/{name}/{newStates}")
  public Response<EmployeeDTO> updateEmployeeStates(@PathVariable(name = "name") String name,
      @PathVariable(name = "newStates") String newStates) {
    return employeeService.updateEmployeeStates(name, newStates);
  }
}
