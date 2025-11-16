package com.education.finalorm.integration;

import com.education.finalorm.dto.request.UserRequest;
import com.education.finalorm.enums.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateStudent() throws Exception {
        UserRequest request = new UserRequest();
        request.setFirstName("Test");
        request.setLastName("Student");
        request.setEmail("test.student@example.com");
        request.setRole(UserRole.STUDENT);
        request.setStudentNo("ST001");

        mockMvc.perform(post("/api/users/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.role").value("STUDENT"));
    }

    @Test
    void shouldReturnNotFoundForNonExistentUser() throws Exception {
        mockMvc.perform(get("/api/users/{id}", "invalid-uuid"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("User not found with id: invalid-uuid"));
    }
}
