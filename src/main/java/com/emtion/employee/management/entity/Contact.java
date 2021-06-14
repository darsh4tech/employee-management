package com.emtion.employee.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String address;
  private String mobile;
  @ManyToOne(optional = false)
  @JsonBackReference
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
