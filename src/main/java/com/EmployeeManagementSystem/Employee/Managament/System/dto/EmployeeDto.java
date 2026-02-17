package com.EmployeeManagementSystem.Employee.Managament.System.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
public class EmployeeDto {

    private Long id;

    @NotNull
    private String firstName;

    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phone_no;

    @NotNull
    @Min(value = 1000)
    private Double salary;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public EmployeeDto(){}


}
