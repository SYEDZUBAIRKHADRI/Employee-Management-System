package com.EmployeeManagementSystem.Employee.Managament.System.service;


import com.EmployeeManagementSystem.Employee.Managament.System.dto.EmployeeDto;
import com.EmployeeManagementSystem.Employee.Managament.System.entity.Employee;
import com.EmployeeManagementSystem.Employee.Managament.System.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private ModelMapper mapper;
    private EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);       //Logging tot track monitor, debugging

    public EmployeeService(ModelMapper mapper, EmployeeRepository employeeRepository) {
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto createEmployee(EmployeeDto newEmployee) {

        Employee employeeToDto = mapper.map(newEmployee,Employee.class); // Dto to entity
        Employee toSaved = employeeRepository.save(employeeToDto);      // save entity
        return mapper.map(toSaved, EmployeeDto.class);                   // entity to dto

    }

    public boolean deleteById(Long empid) {

        if(employeeRepository.existsById(empid)){
           employeeRepository.deleteById(empid);
                                                     logger.warn("Delete suucess");
           return true;
        }
        return false;
    }

    public EmployeeDto findById(Long empid) {
        
        Employee employeeId = employeeRepository.findById(empid)
                .orElseThrow(()-> new RuntimeException("Employee id not exits"+ empid));

        return mapper.map(employeeId, EmployeeDto.class);
    }

    public EmployeeDto updatedEmploye(Long empid, EmployeeDto updatedEmploye) {

        Employee employee = employeeRepository.findById(empid)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        mapper.map(updatedEmploye, Employee.class);
        employee.setId(empid);
        Employee saved = employeeRepository.save(employee);
        return mapper.map(saved, EmployeeDto.class);
    }


    public List<EmployeeDto> findAllEmployees() {
       List<Employee> employees =employeeRepository.findAll();

       return employees.stream()
               .map((emp) -> mapper.map(emp, EmployeeDto.class)).toList(); //Entity to dto
    }

    public EmployeeDto findByEmployeeName(String name) {

        Employee employee =employeeRepository.findByFirstName(name);

        return mapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto findMyNameAndSalary(String name, Double salary) {

       Employee employee = employeeRepository.findByFirstNameAndSalary(name, salary);
       return mapper.map(employee, EmployeeDto.class);
    }
}

 