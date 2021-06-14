package com.emtion.employee.management.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.emtion.employee.management.dto.EmployeeStates;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private Integer age;
  @Enumerated(EnumType.STRING)
  private EmployeeStates employeeStates;
  @JsonManagedReference
  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<Contact> contactList = new ArrayList<>();
}
