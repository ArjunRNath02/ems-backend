package com.finalproject.ems.service.impl;

import com.finalproject.ems.Mapper.AttendanceMapper;
import com.finalproject.ems.dto.AttendanceDto;
import com.finalproject.ems.entity.Attendance;
import com.finalproject.ems.entity.Employee;
import com.finalproject.ems.exception.ResourceNotFoundException;
import com.finalproject.ems.repository.AttendanceRepository;
import com.finalproject.ems.repository.EmployeeRepository;
import com.finalproject.ems.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository attendanceRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public AttendanceDto markAttendance(AttendanceDto dto) {
        if (attendanceRepository.existsByEmployeeIdAndDate(dto.getEmployeeId(), dto.getDate())) {
            throw new RuntimeException("Attendance already marked for this employee on this date");
        }

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee with ID " + dto.getEmployeeId() + " not found"));

        Attendance attendance = AttendanceMapper.mapToAttendance(dto, employee);
        Attendance saved = attendanceRepository.save(attendance);

        return AttendanceMapper.mapToAttendanceDto(saved);
    }

    @Override
    public AttendanceDto getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance with ID " + id + " not found"));

        return AttendanceMapper.mapToAttendanceDto(attendance);
    }

    @Override
    public List<AttendanceDto> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId)
                .stream()
                .map(AttendanceMapper::mapToAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date)
                .stream()
                .map(AttendanceMapper::mapToAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::mapToAttendanceDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto updateAttendance(Long id, AttendanceDto updatedDto) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance with ID " + id + " not found"));

        // Update fields:
        attendance.setStatus(updatedDto.getStatus());
        attendance.setDate(updatedDto.getDate());

        Attendance updated = attendanceRepository.save(attendance);

        return AttendanceMapper.mapToAttendanceDto(updated);
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Attendance with ID " + id + " not found"));

        attendanceRepository.delete(attendance);
    }
}
