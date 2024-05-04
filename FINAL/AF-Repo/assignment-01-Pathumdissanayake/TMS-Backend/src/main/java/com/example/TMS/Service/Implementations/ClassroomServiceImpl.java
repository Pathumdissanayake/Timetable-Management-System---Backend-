package com.example.TMS.Service.Implementations;

import com.example.TMS.Dto.Classroom_Dto;
import com.example.TMS.Entity.Classroom;
import com.example.TMS.Exception.ClassroomAlreadyExistsException;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Mapper.ClassroomMapper;
import com.example.TMS.Repository.ClassRoomInterface;
import com.example.TMS.Service.ClassroomServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomServices {
    private final ClassRoomInterface classInterface;

    @Autowired
    public ClassroomServiceImpl(ClassRoomInterface classInterface, ClassRoomInterface classInterface2) {
        this.classInterface=classInterface2;
    }

    @Override
    public Classroom_Dto createClass(Classroom_Dto classroomDto) {
        Classroom classRoom = ClassroomMapper.mapToClassroom(classroomDto);
        Classroom savedClassroom = classInterface.save(classRoom);
        return ClassroomMapper.mapToClassroom_Dto(savedClassroom);
    }

    @Override
    public Classroom_Dto getClassbyRoomId(String roomId) {
        Classroom classroom = classInterface.findById(roomId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ClassRoom not found with the given room ID "+roomId));
        return ClassroomMapper.mapToClassroom_Dto(classroom);
    }

    @Override
    public List<Classroom_Dto> getAllClasses() {
        List<Classroom> classrooms = classInterface.findAll();
        return classrooms.stream()
                .map(classroom -> ClassroomMapper.mapToClassroom_Dto(classroom))
                .collect(Collectors.toList());
    }


    @Override
    public Classroom_Dto updateClasses(String roomId, Classroom_Dto updatedClass) {
        Classroom classrooms = classInterface.findById(roomId).orElseThrow(
                () -> new ResourceNotFoundException("ClassRoom not found with the given room ID "+roomId)
        );

        classrooms.setAvailability(updatedClass.isAvailability());
        classrooms.setBuilding(updatedClass.getBuilding());
        classrooms.setFloor(updatedClass.getFloor());

        Classroom updated = classInterface.save(classrooms);
        return ClassroomMapper.mapToClassroom_Dto(updated);
    }

    @Override
    public void deleteClass(String roomId) {
        Classroom classroom = classInterface.findById(roomId).orElseThrow(
                () -> new ResourceNotFoundException("ClassRoom not found with the given room ID "+roomId)
        );

        classInterface.deleteById(roomId);

    }
}
