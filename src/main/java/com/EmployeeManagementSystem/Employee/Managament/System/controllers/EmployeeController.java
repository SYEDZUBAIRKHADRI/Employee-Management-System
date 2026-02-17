package com.EmployeeManagementSystem.Employee.Managament.System.controllers;

import com.EmployeeManagementSystem.Employee.Managament.System.dto.EmployeeDto;
import com.EmployeeManagementSystem.Employee.Managament.System.entity.Employee;
import com.EmployeeManagementSystem.Employee.Managament.System.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private  final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeDto createEmployee( @Valid @RequestBody EmployeeDto newEmployee){

        return employeeService.createEmployee(newEmployee);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.findAllEmployees();
    }

    @GetMapping( path = "/{empid}")
    public ResponseEntity<EmployeeDto> getEmpById(@PathVariable  Long empid){
        EmployeeDto employeeDto = employeeService.findById(empid);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping(path = "/search/{name}")
    public EmployeeDto getEmpByName(@PathVariable("name") String firstName){
        return employeeService.findByEmployeeName(firstName);
    }

    @GetMapping(path = "/{name}/{salary}")
    public EmployeeDto findByNameAndSalary(@PathVariable String name,
                                           @PathVariable Double salary){
        return employeeService.findMyNameAndSalary(name,salary);
    }

    @PutMapping(path = "/{empid}")
    public EmployeeDto updateEmployee(@PathVariable Long empid,@RequestBody EmployeeDto updatedEmploye){
        return employeeService.updatedEmploye(empid,updatedEmploye);
    }

    @DeleteMapping(path = "/{empid}")
    public boolean deleteById(@PathVariable Long empid){
        return employeeService.deleteById(empid);
    }
}
