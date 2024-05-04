package com.example.TMS.Mapper;

import com.example.TMS.Dto.Classroom_Dto;
import com.example.TMS.Entity.Classroom;

public class ClassroomMapper {
    public static Classroom_Dto mapToClassroom_Dto(Classroom classroom) {
        return new Classroom_Dto(
                classroom.getRoomId(),
                classroom.getBuilding(),
                classroom.getFloor(),
                classroom.isAvailability()
        );
    }

    public static Classroom mapToClassroom(Classroom_Dto classroomDto) {
        return new Classroom(
                classroomDto.getRoomId(),
                classroomDto.getBuilding(),
                classroomDto.getFloor(),
                classroomDto.isAvailability()
        );
    }
}
