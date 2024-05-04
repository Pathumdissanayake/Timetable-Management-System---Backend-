package com.example.TMS.UnitTest.CourseTest;

import com.example.TMS.Dto.Courses_Dto;
import com.example.TMS.Entity.Courses;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Mapper.CourseMapper;
import com.example.TMS.Repository.CoursesInterface;
import com.example.TMS.Service.Implementations.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCoursesTest {

    @Mock
    private CoursesInterface courseInterface;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void testUpdateCourses_Success() {
        // Mocking the behavior of repository to return an optional course
        Courses existingCourse = new Courses("CourseName", "Code", "Description", "Credits", "Faculty");
        when(courseInterface.findById("Code")).thenReturn(Optional.of(existingCourse));

        // Create a sample updated course DTO
        Courses_Dto updatedDto = new Courses_Dto("UpdatedCourseName", "Code", "UpdatedDescription", "UpdatedCredits", "UpdatedFaculty");

        // Perform the update operation
        Courses updatedCourse = CourseMapper.mapToCourses(updatedDto);
        when(courseInterface.save(any())).thenReturn(updatedCourse);

        // Update the course
        Courses_Dto updatedDtoResult = courseService.updateCourses("Code", updatedDto);

        // Assertions
        assertNotNull(updatedDtoResult);
        assertEquals(updatedDto.getCourseName(), updatedDtoResult.getCourseName());
        assertEquals(updatedDto.getCode(), updatedDtoResult.getCode());
        assertEquals(updatedDto.getDescription(), updatedDtoResult.getDescription());
        assertEquals(updatedDto.getCredits(), updatedDtoResult.getCredits());
        assertEquals(updatedDto.getFaculty(), updatedDtoResult.getFaculty());

        // Verify that findById and save methods are called once
        verify(courseInterface, times(1)).findById("Code");
        verify(courseInterface, times(1)).save(any());
    }

    @Test
    public void testUpdateCourses_NotFound() {
        // Mocking the behavior of repository to return an empty optional
        when(courseInterface.findById("Code")).thenReturn(Optional.empty());

        // Create a sample updated course DTO
        Courses_Dto updatedDto = new Courses_Dto("UpdatedCourseName", "Code", "UpdatedDescription", "UpdatedCredits", "UpdatedFaculty");

        // Assertions
        assertThrows(ResourceNotFoundException.class, () -> {
            // Perform the update operation
            courseService.updateCourses("Code", updatedDto);
        });

        // Verify that findById method is called once
        verify(courseInterface, times(1)).findById("Code");

        // Verify that save method is not called
        verify(courseInterface, never()).save(any());
    }
}
