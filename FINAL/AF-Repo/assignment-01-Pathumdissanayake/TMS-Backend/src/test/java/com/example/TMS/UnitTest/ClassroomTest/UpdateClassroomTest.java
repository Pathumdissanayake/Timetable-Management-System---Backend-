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
public class UpdateClassroomTest {

    @Mock
    private ClassRoomInterface classInterface;

    @InjectMocks
    private ClassroomServiceImpl classroomService;

    @Test
    public void testUpdateClassroom_Success() {
        Classroom existingClassroom = new Classroom("Room1", "BuildingA", "2", true);
        Classroom_Dto updatedDto = new Classroom_Dto("Room1", "BuildingB", "3", false);

        when(classInterface.findById("Room1")).thenReturn(Optional.of(existingClassroom));
        when(classInterface.save(any(Classroom.class))).thenReturn(ClassroomMapper.mapToClassroom(updatedDto));

        Classroom_Dto result = classroomService.updateClasses("Room1", updatedDto);

        assertNotNull(result);
        assertEquals("BuildingB", result.getBuilding());
        assertEquals("3", result.getFloor());
        assertFalse(result.isAvailability());

        verify(classInterface, times(1)).save(any(Classroom.class));
    }

    @Test
    public void testUpdateClassroom_NotFound() {
        Classroom_Dto updatedDto = new Classroom_Dto("Room1", "BuildingB", "3", false);

        when(classInterface.findById("Room1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            classroomService.updateClasses("Room1", updatedDto);
        });

        verify(classInterface, times(1)).findById("Room1");
    }
}
