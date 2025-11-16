package com.education.finalorm.integration;

import com.education.finalorm.dto.request.EnrollmentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EnrollmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldEnrollStudent() throws Exception {
        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentId(UUID.fromString("some-student-uuid"));
        request.setCourseId(UUID.fromString("some-course-uuid"));

        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void shouldGetEnrollmentsByStudent() throws Exception {
        mockMvc.perform(get("/api/enrollments/student/{studentId}", "some-student-uuid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldThrowBusinessExceptionForAlreadyEnrolled() throws Exception {

        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentId(UUID.fromString("some-student-uuid"));
        request.setCourseId(UUID.fromString("some-course-uuid"));

        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Student already enrolled in this course"));
    }
}
