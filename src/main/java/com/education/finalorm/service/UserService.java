package com.education.finalorm.service;

import com.education.finalorm.dto.request.UserRequest;
import com.education.finalorm.dto.response.UserResponse;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.entity.User;
import com.education.finalorm.enums.UserRole;
import com.education.finalorm.exception.BusinessException;
import com.education.finalorm.exception.ResourceNotFoundException;
import com.education.finalorm.mapper.UserMapper;
import com.education.finalorm.repository.StudentRepository;
import com.education.finalorm.repository.TeacherRepository;
import com.education.finalorm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse createUser(UserRequest request, UserRole role) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("User with email already exists");
        }
        User user = userMapper.toEntity(request, role);
        if (user instanceof Student) {
            studentRepository.save((Student) user);
        } else if (user instanceof Teacher) {
            teacherRepository.save((Teacher) user);
        }
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers(UserRole role) {
        return userMapper.toResponseList(userRepository.findByRole(role));
    }

    @Transactional
    public UserResponse updateUser(UUID id, UserRequest request, UserRole role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        if (user instanceof Student) {
            ((Student) user).setStudentNo(request.getStudentNo());
            studentRepository.save((Student) user);
        } else if (user instanceof Teacher) {
            ((Teacher) user).setAcademicTitle(request.getAcademicTitle());
            teacherRepository.save((Teacher) user);
        }
        return userMapper.toResponse(user);
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
