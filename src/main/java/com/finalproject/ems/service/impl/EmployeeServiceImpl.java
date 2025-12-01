package com.finalproject.ems.service.impl;

import com.finalproject.ems.Mapper.EmployeeMapper;
import com.finalproject.ems.dto.EmployeeDto;
import com.finalproject.ems.entity.Department;
import com.finalproject.ems.entity.Employee;
import com.finalproject.ems.exception.ResourceNotFoundException;
import com.finalproject.ems.repository.DepartmentRepository;
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
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Department department = null;

        if (employeeDto.getDepartment() != null && employeeDto.getDepartment().getId() != null) {
            department = departmentRepository.findById(employeeDto.getDepartment().getId())
                    .orElse(null);
        }

        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setRole(employeeDto.getRole());
        employee.setStatus(employeeDto.getStatus());
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saved);
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
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee with id (" + employeeId + ") not found!")
                );

        employee.setFirstName((updatedEmployee.getFirstName()));
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail((updatedEmployee.getEmail()));
        employee.setRole(updatedEmployee.getRole());
        employee.setStatus(updatedEmployee.getStatus());

        if (updatedEmployee.getDepartment() != null) {
            Long deptId = updatedEmployee.getDepartment().getId();

            var department = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Department with id (" + deptId + ") not found!"
                    ));

            employee.setDepartment(department);
        }

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
