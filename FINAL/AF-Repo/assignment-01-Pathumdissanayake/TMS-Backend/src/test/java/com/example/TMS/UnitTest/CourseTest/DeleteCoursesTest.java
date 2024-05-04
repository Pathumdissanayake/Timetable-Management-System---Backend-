package com.example.TMS.UnitTest.CourseTest;

import com.example.TMS.Entity.Courses;
import com.example.TMS.Exception.ResourceNotFoundException;
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
public class DeleteCoursesTest {

    @Mock
    private CoursesInterface courseInterface;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    public void testDeleteCourses_Success() {
        // Mocking the behavior of repository to return an optional course
        Courses existingCourse = new Courses("CourseName", "Code", "Description", "Credits", "Faculty");
        when(courseInterface.findById("Code")).thenReturn(Optional.of(existingCourse));

        // Perform the delete operation
        assertDoesNotThrow(() -> {
            courseService.deleteCourses("Code");
        });

        // Verify that deleteById is called once
        verify(courseInterface, times(1)).deleteById("Code");
    }

    @Test
    public void testDeleteCourses_NotFound() {
        // Mocking the behavior of repository to return an empty optional
        when(courseInterface.findById("Code")).thenReturn(Optional.empty());

        // Assertions
        assertThrows(ResourceNotFoundException.class, () -> {
            // Perform the delete operation
            courseService.deleteCourses("Code");
        });

        // Verify that deleteById is not called
        verify(courseInterface, never()).deleteById("Code");
    }
}
