package com.education.finalorm.service;

import com.education.finalorm.dto.request.QuizRequest;
import com.education.finalorm.dto.response.QuizResponse;
import com.education.finalorm.entity.Module;
import com.education.finalorm.entity.Quiz;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.QuizMapper;
import com.education.finalorm.repository.ModuleRepository;
import com.education.finalorm.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final ModuleRepository moduleRepository;
    private final QuizMapper quizMapper;

    @Transactional
    public QuizResponse createQuiz(QuizRequest request) {
        Module module = moduleRepository.findById(request.getModuleId())
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        Quiz quiz = quizMapper.toEntity(request);
        quiz.setModule(module);
        quiz = quizRepository.save(quiz);
        module.setQuiz(quiz); // Связь 1-1
        moduleRepository.save(module);
        return quizMapper.toResponse(quiz);
    }

    @Transactional(readOnly = true)
    public QuizResponse getQuizById(UUID id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
        return quizMapper.toResponse(quiz);
    }
}
