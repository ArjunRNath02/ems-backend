package com.finalproject.ems.service.impl;

import com.finalproject.ems.Mapper.DepartmentMapper;
import com.finalproject.ems.dto.DepartmentDto;
import com.finalproject.ems.entity.Department;
import com.finalproject.ems.entity.Employee;
import com.finalproject.ems.exception.ResourceNotFoundException;
import com.finalproject.ems.repository.DepartmentRepository;
import com.finalproject.ems.repository.EmployeeRepository;
import com.finalproject.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsByName(departmentDto.getName())) {
            throw new RuntimeException("Department with name '" + departmentDto.getName() + "' already exists.");
        }

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department saved = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(saved);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department with ID " + departmentId + " not found"));

        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department with ID " + departmentId + " not found"));

        Long oldHodId = department.getHeadOfDepartment();
        Long newHodId = updatedDepartment.getHeadOfDepartment();

        department.setName(updatedDepartment.getName());
        department.setDescription(updatedDepartment.getDescription());
        department.setHeadOfDepartment(newHodId);

        Department updated = departmentRepository.save(department);

        if (!Objects.equals(oldHodId, newHodId)) {
            if (oldHodId != null) {
                Employee oldHod = employeeRepository.findById(oldHodId).orElse(null);
                if (oldHod != null) {
                    oldHod.setRole("EMPLOYEE");
                    employeeRepository.save(oldHod);
                }
            }

            if (newHodId != null) {
                Employee newHod = employeeRepository.findById(newHodId).orElse(null);
                if (newHod != null) {
                    newHod.setRole("HOD");
                    employeeRepository.save(newHod);
                }
            }
        }

        return DepartmentMapper.mapToDepartmentDto(updated);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department with ID " + departmentId + " not found"));

        departmentRepository.delete(department);
    }
}
