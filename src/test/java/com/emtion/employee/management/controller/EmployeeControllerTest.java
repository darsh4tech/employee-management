package com.emtion.employee.management.controller;

import static org.hamcrest.CoreMatchers.containsString;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.emtion.employee.management.dto.ContactDTO;
import com.emtion.employee.management.dto.EmployeeDTO;
import com.emtion.employee.management.entity.Contact;
import com.emtion.employee.management.entity.Employee;
import com.emtion.employee.management.repository.EmployeeRepository;
import com.emtion.employee.management.service.EmployeeService;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
// @ActiveProfiles("test")
class EmployeeControllerTest {

  private static final Logger log = LoggerFactory.getLogger(EmployeeControllerTest.class);

  @Autowired
  MockMvc mockMvc;

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  EmployeeService employeeService;

  private Employee employee;
  private Contact contact;
  private List<Contact> contactList = new ArrayList<>();

  @Test
  @DisplayName("createEmployeeWithNameAlreadyExistBefore_ShouldThrowException")
  void createEmployeeWithNameAlreadyExistBefore_ShouldThrowException() throws Exception {

    employee = new Employee();
    employee.setAge(26);
    employee.setName("MMHassan");
    contact = new Contact();
    contact.setAddress("42 Ahmed Oraby Cairo");
    contact.setMobile("01123456789");
    contact.setEmployee(employee);
    contactList.add(contact);
    employee.setContactList(contactList);
    employeeRepository.save(employee);
    log.info("Saved employee: {}", employee);


    EmployeeDTO employeeDTO = EmployeeDTO.builder().age(30).name("MMHassan").build();
    ContactDTO contactDTO = new ContactDTO();
    contactDTO.setAddress("42 Ahmed Oraby Cairo");
    contactDTO.setMobile("01123456789");
    List<ContactDTO> contactList = new ArrayList<>();
    contactList.add(contactDTO);
    employeeDTO.setContactList(contactList);
    String json = new Gson().toJson(employeeDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/employee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(
            MockMvcResultMatchers.content().string(containsString("Employee name already exist")));

  }

}
