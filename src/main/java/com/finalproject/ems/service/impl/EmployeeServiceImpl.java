package com.finalproject.ems.service.impl;

import com.finalproject.ems.Mapper.EmployeeMapper;
import com.finalproject.ems.dto.EmployeeDto;
import com.finalproject.ems.entity.Employee;
import com.finalproject.ems.exception.ResourceNotFoundException;
import com.finalproject.ems.repository.EmployeeRepository;
import com.finalproject.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto((savedEmployee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with the given Id ("+ employeeId+") in the database!"));
        return EmployeeMapper.mapToEmployeeDto((employee));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto((employee)))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id ("+employeeId+") not found!")
        );

        employee.setFirstName((updatedEmployee.getFirstName()));
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail((updatedEmployee.getEmail()));

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id ("+employeeId+") not found!")
        );

        employeeRepository.deleteById(employeeId);
    }
}
