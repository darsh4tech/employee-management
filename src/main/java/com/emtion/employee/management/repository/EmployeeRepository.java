package com.emtion.employee.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.emtion.employee.management.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  List<Employee> findByNameIgnoreCase(String name);

}
