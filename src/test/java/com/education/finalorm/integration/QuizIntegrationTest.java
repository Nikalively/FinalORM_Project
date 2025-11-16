package com.education.finalorm.integration;

import com.education.finalorm.dto.request.QuizRequest;
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
class QuizIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateQuiz() throws Exception {
        QuizRequest request = new QuizRequest();
        request.setTitle("Test Quiz");
        request.setDescription("Description");
        request.setModuleId(UUID.fromString("some-module-uuid"));

        mockMvc.perform(post("/api/quizzes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Quiz"));
    }

    @Test
    void shouldGetQuizById() throws Exception {
        mockMvc.perform(get("/api/quizzes/{id}", "some-quiz-uuid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Quiz"));
    }

    @Test
    void shouldReturnNotFoundForNonExistentQuiz() throws Exception {
        mockMvc.perform(get("/api/quizzes/{id}", "invalid-uuid"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Quiz not found with id: invalid-uuid"));
    }
}
