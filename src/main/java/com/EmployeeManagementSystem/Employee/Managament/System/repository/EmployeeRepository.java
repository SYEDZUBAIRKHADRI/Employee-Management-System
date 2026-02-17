package com.EmployeeManagementSystem.Employee.Managament.System.repository;

import com.EmployeeManagementSystem.Employee.Managament.System.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    Employee findByFirstName(String firstName);


    Employee findByFirstNameAndSalary(String firstName, Double salary);


}
