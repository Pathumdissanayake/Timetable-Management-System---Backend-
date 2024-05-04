package com.example.TMS.UnitTest.ClassroomTest;

import com.example.TMS.Dto.Classroom_Dto;
import com.example.TMS.Entity.Classroom;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Mapper.ClassroomMapper;
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
public class ReadClassroomTest {

    @Mock
    private ClassRoomInterface classInterface;

    @InjectMocks
    private ClassroomServiceImpl classroomService;

    @Test
    public void testGetClassroomById_Success() {
        Classroom_Dto classroomDto = new Classroom_Dto("Room1", "BuildingA", "2", true);

        Classroom foundClassroom = ClassroomMapper.mapToClassroom(classroomDto);
        when(classInterface.findById("Room1")).thenReturn(Optional.of(foundClassroom));

        Classroom_Dto result = classroomService.getClassbyRoomId("Room1");

        assertNotNull(result);
        assertEquals("Room1", result.getRoomId());
        assertEquals("BuildingA", result.getBuilding());
        assertEquals("2", result.getFloor());
        assertTrue(result.isAvailability());

        verify(classInterface, times(1)).findById("Room1");
    }

    @Test
    public void testGetClassroomById_NotFound() {
        when(classInterface.findById("Room1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            classroomService.getClassbyRoomId("Room1");
        });

        verify(classInterface, times(1)).findById("Room1");
    }
}
