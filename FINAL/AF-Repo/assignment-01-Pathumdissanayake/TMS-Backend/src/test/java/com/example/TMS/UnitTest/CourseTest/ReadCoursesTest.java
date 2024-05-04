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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReadCoursesTest {

    @Mock
    private CoursesInterface courseInterface;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void testGetCourseByCode_Success() {
        // Mocking the behavior of repository to return an optional course
        Courses course = new Courses("CourseName", "Code", "Description", "Credits", "Faculty");
        when(courseInterface.findById("Code")).thenReturn(Optional.of(course));

        // Perform the get operation
        Courses_Dto retrievedDto = courseService.getCourseByCode("Code");

        // Assertions
        assertNotNull(retrievedDto);
        assertEquals(course.getCourseName(), retrievedDto.getCourseName());
        assertEquals(course.getCode(), retrievedDto.getCode());
        assertEquals(course.getDescription(), retrievedDto.getDescription());
        assertEquals(course.getCredits(), retrievedDto.getCredits());
        assertEquals(course.getFaculty(), retrievedDto.getFaculty());
    }

    @Test
    public void testGetCourseByCode_NotFound() {
        // Mocking the behavior of repository to return an empty optional
        when(courseInterface.findById("Code")).thenReturn(Optional.empty());

        // Assertions
        assertThrows(ResourceNotFoundException.class, () -> {
            // Perform the get operation
            courseService.getCourseByCode("Code");
        });
    }

    @Test
    public void testGetAllCourses_Success() {
        // Mocking the behavior of repository to return a list of courses
        List<Courses> coursesList = new ArrayList<>();
        coursesList.add(new Courses("CourseName1", "Code1", "Description1", "Credits1", "Faculty1"));
        coursesList.add(new Courses("CourseName2", "Code2", "Description2", "Credits2", "Faculty2"));
        when(courseInterface.findAll()).thenReturn(coursesList);

        // Perform the get operation
        List<Courses_Dto> retrievedDtoList = courseService.getAllCourses();

        // Assertions
        assertNotNull(retrievedDtoList);
        assertEquals(2, retrievedDtoList.size());

        // Validate the first course
        Courses_Dto dto1 = retrievedDtoList.get(0);
        assertEquals(coursesList.get(0).getCourseName(), dto1.getCourseName());
        assertEquals(coursesList.get(0).getCode(), dto1.getCode());
        assertEquals(coursesList.get(0).getDescription(), dto1.getDescription());
        assertEquals(coursesList.get(0).getCredits(), dto1.getCredits());
        assertEquals(coursesList.get(0).getFaculty(), dto1.getFaculty());

        // Validate the second course
        Courses_Dto dto2 = retrievedDtoList.get(1);
        assertEquals(coursesList.get(1).getCourseName(), dto2.getCourseName());
        assertEquals(coursesList.get(1).getCode(), dto2.getCode());
        assertEquals(coursesList.get(1).getDescription(), dto2.getDescription());
        assertEquals(coursesList.get(1).getCredits(), dto2.getCredits());
        assertEquals(coursesList.get(1).getFaculty(), dto2.getFaculty());
    }

    @Test
    public void testGetAllCourses_EmptyList() {
        // Mocking the behavior of repository to return an empty list
        when(courseInterface.findAll()).thenReturn(new ArrayList<>());

        // Perform the get operation
        List<Courses_Dto> retrievedDtoList = courseService.getAllCourses();

        // Assertions
        assertNotNull(retrievedDtoList);
        assertTrue(retrievedDtoList.isEmpty());
    }
}
