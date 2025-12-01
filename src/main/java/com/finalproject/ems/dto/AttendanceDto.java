package com.finalproject.ems.dto;

import com.finalproject.ems.status.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AttendanceDto {
    private Long id;
    private Long employeeId;
    private LocalDate date;
    private AttendanceStatus status;
}
