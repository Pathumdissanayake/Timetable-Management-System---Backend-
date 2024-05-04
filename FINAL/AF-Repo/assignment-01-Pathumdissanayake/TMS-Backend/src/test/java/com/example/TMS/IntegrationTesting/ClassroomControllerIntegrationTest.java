package com.example.TMS.IntegrationTesting;

import com.example.TMS.Dto.Classroom_Dto;
import com.example.TMS.Service.ClassroomServices;
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
public class ClassroomControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClassroomServices classroomServices;

    @BeforeEach
    public void setup() {
        // Add a classroom to ensure it exists for tests
        Classroom_Dto classroom = new Classroom_Dto();
        classroom.setRoomId("room101");
        classroom.setBuilding("Main Building");
        classroom.setFloor("3rd Floor");
        classroom.setAvailability(true);
        classroomServices.createClass(classroom);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateClassroom() throws Exception {
        // Creating a new classroom
        String classroomJson = "{\"roomId\":\"room102\",\"building\":\"New Building\",\"floor\":\"1st Floor\",\"availability\":true}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/classrooms/admin/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(classroomJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.roomId").value("room102"))
                .andExpect(jsonPath("$.building").value("New Building"))
                .andExpect(jsonPath("$.floor").value("1st Floor"))
                .andExpect(jsonPath("$.availability").value(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetClassByRoomId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/classrooms/user/get/room101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value("room101"))
                .andExpect(jsonPath("$.building").value("Main Building"))
                .andExpect(jsonPath("$.floor").value("3rd Floor"))
                .andExpect(jsonPath("$.availability").value(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteClassroom() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/classrooms/admin/delete/room101"))
                .andExpect(status().isOk())
                .andExpect(content().string("Classroom Deleted Successfully"));
    }
}
