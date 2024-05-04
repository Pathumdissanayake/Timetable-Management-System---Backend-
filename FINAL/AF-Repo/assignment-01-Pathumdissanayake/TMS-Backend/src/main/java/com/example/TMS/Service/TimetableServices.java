package com.example.TMS.Service;

import com.example.TMS.Dto.Timetable_Dto;

import java.util.List;
public interface TimetableServices {

    Timetable_Dto createTimetables(Timetable_Dto timetableDto);
    Timetable_Dto getTimetableByTableId(String tableId);
    List<Timetable_Dto> getAllTimetables();
    Timetable_Dto updateTimetable(String tableId, Timetable_Dto updatedTimetable);
    void DeleteTimetable(String tableId);

}
