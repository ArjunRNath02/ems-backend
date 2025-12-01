package com.finalproject.ems.service;

import com.finalproject.ems.dto.AttendanceDto;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceDto markAttendance(AttendanceDto attendanceDto);

    AttendanceDto getAttendanceById(Long id);

    List<AttendanceDto> getAttendanceByEmployee(Long employeeId);

    List<AttendanceDto> getAttendanceByDate(LocalDate date);

    List<AttendanceDto> getAllAttendance();

    AttendanceDto updateAttendance(Long id, AttendanceDto updatedAttendance);

    void deleteAttendance(Long id);
}
