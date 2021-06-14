package com.emtion.employee.management.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.emtion.employee.management.dto.EmployeeDTO;
import com.emtion.employee.management.dto.EmployeeStates;
import com.emtion.employee.management.dto.Response;
import com.emtion.employee.management.entity.Contact;
import com.emtion.employee.management.entity.Employee;
import com.emtion.employee.management.repository.EmployeeRepository;
import com.emtion.employee.management.validation.EmployeeValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;
  private EmployeeValidator employeeValidator;
  private ModelMapper modelMapper;

  public EmployeeService(EmployeeRepository employeeRepository, EmployeeValidator employeeValidator,
      ModelMapper modelMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeValidator = employeeValidator;
    this.modelMapper = modelMapper;
  }

  public Response<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO) {

    Response<EmployeeDTO> response = new Response<>();
    try {

      response = employeeValidator.validateAddEmployee(employeeDTO);
      if (!response.getHttpStatus().equals(HttpStatus.ACCEPTED)) {
        return response;
      }

      Employee employee = modelMapper.map(employeeDTO, Employee.class);
      List<Contact> list = employeeDTO.getContactList().stream().map(contactDto -> {
        Contact contact = modelMapper.map(contactDto, Contact.class);
        contact.setEmployee(employee);
        log.info("service - contact: {}", contact);
        return contact;
      }).collect(Collectors.toList());
      employee.setContactList(list);
      employee.setEmployeeStates(EmployeeStates.ADDED);
      employeeRepository.save(employee);

      response.setHttpStatus(HttpStatus.CREATED);
      response.setData(employeeDTO);
      return response;
    } catch (Exception e) {
      log.error("Error: {}", e);
      response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
      return response;
    }
  }

  public Response<EmployeeDTO> updateEmployeeStates(String name, String newStates) {

    Response<EmployeeDTO> response = new Response<>();
    try {

      List<Employee> employeeList = employeeRepository.findByNameIgnoreCase(name.trim());

      if (employeeList == null || employeeList.size() == 0) {
        response.setHttpStatus(HttpStatus.NO_CONTENT);
        response.setMsg("no employee with this name: " + name);
        return response;
      }

      EmployeeStates employeeStates = null;
      for (EmployeeStates state : EmployeeStates.values()) {
        if (state.name().equals(newStates)) {
          employeeStates = EmployeeStates.valueOf(newStates);
          break;
        }
      }

      if (employeeStates == null) {
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMsg("the provided state not valid: " + employeeStates);
        return response;
      }
      Employee employee = employeeList.get(0);
      employee.setEmployeeStates(employeeStates);
      EmployeeDTO employeeDTO =
          modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);

      response.setData(employeeDTO);
      response.setHttpStatus(HttpStatus.OK);
      return response;
    } catch (Exception e) {
      return response;
    }
  }

}
