package com.finalproject.ems.controller;

import com.finalproject.ems.dto.AttendanceDto;
import com.finalproject.ems.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/attendance")

public class AttendanceController {
    private AttendanceService attendanceService;

    // CREATE Attendance Record
    @PostMapping
    public ResponseEntity<AttendanceDto> markAttendance(@RequestBody AttendanceDto attendanceDto) {
        AttendanceDto saved = attendanceService.markAttendance(attendanceDto);
        return ResponseEntity.ok(saved);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable Long attendanceId) {
        AttendanceDto attendance = attendanceService.getAttendanceById(attendanceId);
        return ResponseEntity.ok(attendance);
    }

    // GET ATTENDANCE BY EMPLOYEE
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByEmployee(employeeId));
    }

    // GET ATTENDANCE BY DATE
    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceByDate(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(parsedDate));
    }

    // GET ALL ATTENDANCE
    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    // UPDATE ATTENDANCE
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(
            @PathVariable Long attendanceId,
            @RequestBody AttendanceDto updatedAttendance
    ) {
        AttendanceDto updated = attendanceService.updateAttendance(attendanceId, updatedAttendance);
        return ResponseEntity.ok(updated);
    }

    // DELETE ATTENDANCE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
