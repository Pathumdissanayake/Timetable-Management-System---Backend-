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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCoursesTest {

    @Mock
    private CoursesInterface courseInterface;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void testCreateCourses_Success() {
        // Create a sample course DTO
        Courses_Dto courseDto = new Courses_Dto("CourseName", "Code", "Description", "Credits", "Faculty");

        // Mock the behavior of repository to return saved course
        Courses savedCourse = CourseMapper.mapToCourses(courseDto);
        when(courseInterface.save(any())).thenReturn(savedCourse);

        // Perform the create operation
        Courses_Dto savedDto = courseService.createCourses(courseDto);

        // Assertions
        assertNotNull(savedDto);
        assertEquals(courseDto.getCourseName(), savedDto.getCourseName());
        assertEquals(courseDto.getCode(), savedDto.getCode());
        assertEquals(courseDto.getDescription(), savedDto.getDescription());
        assertEquals(courseDto.getCredits(), savedDto.getCredits());
        assertEquals(courseDto.getFaculty(), savedDto.getFaculty());

        // Verify that save method is called once
        verify(courseInterface, times(1)).save(any());
    }

    @Test
    public void testCreateCourses_Failure() {
        // Create a sample course DTO
        Courses_Dto courseDto = new Courses_Dto("CourseName", "Code", "Description", "Credits", "Faculty");

        // Mock the behavior of repository to throw an exception
        when(courseInterface.save(any())).thenThrow(RuntimeException.class);

        // Assertions
        assertThrows(RuntimeException.class, () -> {
            // Perform the create operation
            courseService.createCourses(courseDto);
        });

        // Verify that save method is called once
        verify(courseInterface, times(1)).save(any());
    }
}
