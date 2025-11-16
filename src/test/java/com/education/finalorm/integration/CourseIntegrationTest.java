package com.education.finalorm.integration;

import com.education.finalorm.dto.request.CourseRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCourse() throws Exception {

        CourseRequest request = new CourseRequest();
        request.setTitle("Test Course");
        request.setDescription("Description");
        request.setCategoryId(UUID.fromString("some-category-uuid"));
        request.setTeacherId(UUID.fromString("some-teacher-uuid"));
        request.setTagIds(List.of(UUID.fromString("some-tag-uuid")));

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Course"));
    }

    @Test
    void shouldGetCourseById() throws Exception {

        mockMvc.perform(get("/api/courses/{id}", "some-course-uuid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Introduction to Java"));
    }

    @Test
    void shouldSearchCourses() throws Exception {
        mockMvc.perform(get("/api/courses/search")
                        .param("query", "Java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Introduction to Java"));
    }

    @Test
    void shouldReturnNotFoundForNonExistentCourse() throws Exception {
        mockMvc.perform(get("/api/courses/{id}", "invalid-uuid"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Course not found with id: invalid-uuid"));
    }
}
