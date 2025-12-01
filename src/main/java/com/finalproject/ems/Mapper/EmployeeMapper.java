package com.finalproject.ems.Mapper;

import com.finalproject.ems.dto.EmployeeDto;
import com.finalproject.ems.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment() != null ?
                        DepartmentMapper.mapToDepartmentDto(employee.getDepartment()) : null,
                employee.getRole(),
                employee.getStatus(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

    public static Employee mapToEmployee(EmployeeDto dto) {
        Employee emp = new Employee();

        emp.setId(dto.getId());
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setRole(dto.getRole());
        emp.setStatus(dto.getStatus());
        emp.setCreatedAt(dto.getCreatedAt());
        emp.setUpdatedAt(dto.getUpdatedAt());

        return emp;
    }

}
