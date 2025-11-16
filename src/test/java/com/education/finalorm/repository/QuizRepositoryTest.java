package com.education.finalorm.repository;

import com.education.finalorm.entity.Quiz;
import com.education.finalorm.entity.Module;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class QuizRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuizRepository quizRepository;

    @Test
    void shouldSaveAndFindQuiz() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Вася");
        teacher.setLastName("Петров");
        teacher.setEmail("vasia@example.com");
        teacher.setRole(UserRole.TEACHER);
        teacher.setAcademicTitle("Professor");
        entityManager.persistAndFlush(teacher);

        Course course = new Course();
        course.setTitle("Java Basics");
        course.setTeacher(teacher);
        entityManager.persistAndFlush(course);

        Module module = new Module();
        module.setTitle("Module 1");
        module.setCourse(course);
        module.setOrderIndex(1);
        entityManager.persistAndFlush(module);

        Quiz quiz = new Quiz();
        quiz.setTitle("Quiz 1");
        quiz.setDescription("Description");
        quiz.setModule(module);
        entityManager.persistAndFlush(quiz);

        entityManager.clear();


        Optional<Quiz> found = quizRepository.findById(quiz.getId());


        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Quiz 1");
        assertThat(found.get().getModule().getTitle()).isEqualTo("Module 1");
    }

    @Test
    void shouldReturnEmptyForNonExistentQuiz() {
        Optional<Quiz> quiz = quizRepository.findById(java.util.UUID.randomUUID());
        assertThat(quiz).isEmpty();
    }
}
