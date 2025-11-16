package com.education.finalorm.repository;

import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.User;
import com.education.finalorm.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserByEmail() {

        Student student = new Student();
        student.setFirstName("Test");
        student.setLastName("User");
        student.setEmail("test@example.com");
        student.setRole(UserRole.STUDENT);
        student.setBirthDate(LocalDate.now());
        student.setStudentNo("ST001");
        entityManager.persistAndFlush(student);
        entityManager.clear();


        User found = userRepository.findByEmail("test@example.com").orElse(null);


        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void shouldReturnEmptyForNonExistentEmail() {
        assertThat(userRepository.findByEmail("nonexistent@example.com")).isEmpty();
    }
}
