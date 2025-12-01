package com.finalproject.ems.Mapper;

import com.finalproject.ems.dto.AttendanceDto;
import com.finalproject.ems.entity.Attendance;
import com.finalproject.ems.entity.Employee;

public class AttendanceMapper {

    public static AttendanceDto mapToAttendanceDto(Attendance attendance) {
        return new AttendanceDto(
                attendance.getId(),
                attendance.getEmployee().getId(),
                attendance.getDate(),
                attendance.getStatus()
        );
    }

    public static Attendance mapToAttendance(AttendanceDto dto, Employee employee) {
        Attendance attendance = new Attendance();
        attendance.setId(dto.getId());
        attendance.setEmployee(employee);
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());
        return attendance;
    }
}
