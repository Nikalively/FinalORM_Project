package com.education.finalorm.controller;

import com.education.finalorm.dto.request.UserRequest;
import com.education.finalorm.dto.response.UserResponse;
import com.education.finalorm.enums.UserRole;
import com.education.finalorm.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/students")
    public ResponseEntity<UserResponse> createStudent(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.createUser(request, UserRole.STUDENT);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/teachers")
    public ResponseEntity<UserResponse> createTeacher(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.createUser(request, UserRole.TEACHER);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponse>> getUsersByRole(@PathVariable UserRole role) {
        List<UserResponse> response = userService.getAllUsers(role);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequest request, @RequestParam UserRole role) {
        UserResponse response = userService.updateUser(id, request, role);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
