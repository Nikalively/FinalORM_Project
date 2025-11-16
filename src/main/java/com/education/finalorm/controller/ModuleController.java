package com.education.finalorm.controller;

import com.education.finalorm.dto.request.ModuleRequest;
import com.education.finalorm.dto.response.ModuleResponse;
import com.education.finalorm.service.ModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<ModuleResponse> createModule(@Valid @RequestBody ModuleRequest request) {
        ModuleResponse response = moduleService.createModule(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ModuleResponse>> getModulesByCourse(@PathVariable UUID courseId) {
        List<ModuleResponse> response = moduleService.getModulesByCourse(courseId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleResponse> updateModule(@PathVariable UUID id, @Valid @RequestBody ModuleRequest request) {
        ModuleResponse response = moduleService.updateModule(id, request);
        return ResponseEntity.ok(response);
    }
}
