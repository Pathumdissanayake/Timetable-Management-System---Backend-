package com.example.TMS.Service;

import com.example.TMS.Dto.Classroom_Dto;

import java.util.List;

public interface ClassroomServices {

    Classroom_Dto createClass(Classroom_Dto classroomDto);
    Classroom_Dto getClassbyRoomId(String roomId);

    List<Classroom_Dto> getAllClasses();

    Classroom_Dto updateClasses(String roomId, Classroom_Dto updatedClass);

    void deleteClass(String roomId);
}
