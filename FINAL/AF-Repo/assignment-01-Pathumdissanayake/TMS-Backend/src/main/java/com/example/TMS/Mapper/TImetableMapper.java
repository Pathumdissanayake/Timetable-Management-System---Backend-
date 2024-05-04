package com.example.TMS.Mapper;

import com.example.TMS.Dto.Timetable_Dto;
import com.example.TMS.Entity.Timetable;

public class TImetableMapper {

    public static Timetable_Dto mapToTimetable_Dto(Timetable timetable) {
        return new Timetable_Dto(
                timetable.getTableId(),
                timetable.getFaculty(),
                timetable.getLecturer(),
                timetable.getDate(),
                timetable.getTimeStart(),
                timetable.getTimeEnd(),
                timetable.getCourseId(),
                timetable.getRoomId(),
                timetable.getResourceId()
        );
    }

    public static Timetable mapToTimetable(Timetable_Dto timetableDto) {
        return new Timetable(
                timetableDto.getTableId(),
                timetableDto.getFaculty(),
                timetableDto.getLecturer(),
                timetableDto.getDate(),
                timetableDto.getTimeStart(),
                timetableDto.getTimeEnd(),
                timetableDto.getCourseId(),
                timetableDto.getRoomId(),
                timetableDto.getResourceId()
        );
    }
}
