package com.finalproject.ems.Mapper;

import com.finalproject.ems.dto.DepartmentDto;
import com.finalproject.ems.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getHeadOfDepartment()
        );
    }

    public static Department mapToDepartment(DepartmentDto dto) {
        Department department = new Department();

        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setHeadOfDepartment(dto.getHeadOfDepartment());

        return department;
    }
}
