package com.example.TMS.Dto;

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

public class Timetable_Dto {

    private String tableId;
    private String faculty;
    private String lecturer;
    private String date;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private String courseId;
    private String roomId;
    private String resourceId;
}
