package com.education.finalorm.integration;

import com.education.finalorm.dto.request.AssignmentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAssignment() throws Exception {
        AssignmentRequest request = new AssignmentRequest();
        request.setTitle("Test Assignment");
        request.setDescription("Description");
        request.setDueDate(LocalDate.now().plusDays(7));
        request.setMaxScore(100.0);
        request.setLessonId(UUID.fromString("some-lesson-uuid"));

        mockMvc.perform(post("/api/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Assignment"));
    }

    @Test
    void shouldGetAssignmentsByLesson() throws Exception {
        mockMvc.perform(get("/api/assignments/lesson/{lessonId}", "some-lesson-uuid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnNotFoundForNonExistentLesson() throws Exception {
        AssignmentRequest request = new AssignmentRequest();
        request.setTitle("Test");
        request.setDescription("Desc");
        request.setDueDate(LocalDate.now());
        request.setMaxScore(10.0);
        request.setLessonId(UUID.fromString("invalid-uuid"));

        mockMvc.perform(post("/api/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Lesson not found"));
    }
}
