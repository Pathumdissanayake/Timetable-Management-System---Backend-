package com.example.TMS.UnitTest.ClassroomTest;

import com.example.TMS.Dto.Classroom_Dto;
import com.example.TMS.Entity.Classroom;
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
public class CreateClassroomTest {

    @Mock
    private ClassRoomInterface classInterface;

    @InjectMocks
    private ClassroomServiceImpl classroomService;

    @Test
    public void testCreateClassroom_Success() {
        Classroom_Dto classroomDto = new Classroom_Dto("Room1", "BuildingA", "2", true);

        // Mock save behavior to return a newly created classroom
        Classroom savedClassroom = ClassroomMapper.mapToClassroom(classroomDto);
        when(classInterface.save(any(Classroom.class))).thenReturn(savedClassroom);

        // Perform creation operation
        Classroom_Dto result = classroomService.createClass(classroomDto);

        // Assert the creation was successful
        assertNotNull(result);
        assertEquals("Room1", result.getRoomId());
        assertEquals("BuildingA", result.getBuilding());
        assertEquals("2", result.getFloor());
        assertTrue(result.isAvailability());

        // Verify the save method was called once
        verify(classInterface, times(1)).save(any(Classroom.class));
    }

    @Test
    public void testCreateClassroom_Failure() {
        // Create a sample classroom DTO for testing
        Classroom_Dto classroomDto = new Classroom_Dto("Room1", "BuildingA", "2", true);

        // Mock an unexpected failure during the save operation
        when(classInterface.save(any(Classroom.class))).thenThrow(new RuntimeException("Database error"));

        // Expect a RuntimeException or other relevant exception during classroom creation
        Exception thrownException = assertThrows(
                RuntimeException.class,
                () -> classroomService.createClass(classroomDto),
                "Expected a RuntimeException due to a database error during classroom creation"
        );

        // Verify the exception message to ensure it's the expected failure reason
        assertTrue(thrownException.getMessage().contains("Database error"), "Unexpected error message");

        // Ensure that the save method was indeed called, leading to the failure
        verify(classInterface, times(1)).save(any(Classroom.class));

        // Since the operation failed, no additional interactions are expected
        verify(classInterface, times(0)).findById(anyString());
    }


}
