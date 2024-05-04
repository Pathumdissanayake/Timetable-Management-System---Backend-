package com.example.TMS.IntegrationTesting;

import com.example.TMS.Dto.Courses_Dto;
import com.example.TMS.Service.CourseServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseServices courseServices;

    @BeforeEach
    public void setup() {
        // Add a course to ensure it exists for tests
        Courses_Dto course = new Courses_Dto();
        course.setCode("CS101");
        course.setCourseName("Introduction to Computer Science");
        course.setDescription("Basic concepts in computer science");
        course.setCredits("3");
        course.setFaculty("Dr. Smith");
        courseServices.createCourses(course);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateCourse() throws Exception {
        String courseJson = "{\"courseName\":\"Data Structures\",\"code\":\"CS102\",\"description\":\"Study of data structures\",\"credits\":\"4\",\"faculty\":\"Dr. Jones\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/courses/admin/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("CS102"))
                .andExpect(jsonPath("$.courseName").value("Data Structures"))
                .andExpect(jsonPath("$.description").value("Study of data structures"))
                .andExpect(jsonPath("$.credits").value("4"))
                .andExpect(jsonPath("$.faculty").value("Dr. Jones"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetCourseByCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/user/get/CS101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("CS101"))
                .andExpect(jsonPath("$.courseName").value("Introduction to Computer Science"))
                .andExpect(jsonPath("$.description").value("Basic concepts in computer science"))
                .andExpect(jsonPath("$.credits").value("3"))
                .andExpect(jsonPath("$.faculty").value("Dr. Smith"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateCourse() throws Exception {
        String updatedCourseJson = "{\"courseName\":\"Advanced Computer Science\",\"code\":\"CS101\",\"description\":\"Advanced concepts in computer science\",\"credits\":\"5\",\"faculty\":\"Dr. Smith\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/courses/admin/update/CS101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCourseJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value("Advanced Computer Science"))
                .andExpect(jsonPath("$.credits").value("5"))
                .andExpect(jsonPath("$.description").value("Advanced concepts in computer science"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/courses/admin/delete/CS101"))
                .andExpect(status().isOk())
                .andExpect(content().string("Course Deleted Successfully"));
    }
}
