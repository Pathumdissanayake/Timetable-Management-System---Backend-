package com.example.TMS.UnitTest.ClassroomTest;

import com.example.TMS.Entity.Classroom;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Repository.ClassRoomInterface;
import com.example.TMS.Service.Implementations.ClassroomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeleteClassroomTest {

    @Mock
    private ClassRoomInterface classInterface;

    @InjectMocks
    private ClassroomServiceImpl classroomService;

    @Test
    public void testDeleteClassroom_Success() {
        when(classInterface.findById("Room1")).thenReturn(Optional.of(new Classroom("Room1", "BuildingA", "2", true)));

        classroomService.deleteClass("Room1");

        verify(classInterface, times(1)).deleteById("Room1");
    }

    @Test
    public void testDeleteClassroom_NotFound() {
        when(classInterface.findById("Room1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            classroomService.deleteClass("Room1");
        });

        verify(classInterface, times(1)).findById("Room1");
    }
}
