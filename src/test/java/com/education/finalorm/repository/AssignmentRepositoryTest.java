package com.education.finalorm.repository;

import com.education.finalorm.entity.Assignment;
import com.education.finalorm.entity.Lesson;
import com.education.finalorm.entity.Module;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AssignmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Test
    void shouldFindAssignmentsByLesson() {

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

        Lesson lesson = new Lesson();
        lesson.setTitle("Lesson 1");
        lesson.setModule(module);
        entityManager.persistAndFlush(lesson);

        Assignment assignment1 = new Assignment();
        assignment1.setTitle("Assignment 1");
        assignment1.setDescription("Description");
        assignment1.setDueDate(LocalDate.now().plusDays(7));
        assignment1.setMaxScore(100.0);
        assignment1.setLesson(lesson);
        entityManager.persistAndFlush(assignment1);

        Assignment assignment2 = new Assignment();
        assignment2.setTitle("Assignment 2");
        assignment2.setDescription("Description 2");
        assignment2.setDueDate(LocalDate.now().plusDays(14));
        assignment2.setMaxScore(50.0);
        assignment2.setLesson(lesson);
        entityManager.persistAndFlush(assignment2);

        entityManager.clear();


        List<Assignment> assignments = assignmentRepository.findByLessonId(lesson.getId());


        assertThat(assignments).hasSize(2);
        assertThat(assignments.get(0).getTitle()).isEqualTo("Assignment 1");
        assertThat(assignments.get(1).getTitle()).isEqualTo("Assignment 2");
    }

    @Test
    void shouldReturnEmptyListForNonExistentLesson() {
        List<Assignment> assignments = assignmentRepository.findByLessonId(java.util.UUID.randomUUID());
        assertThat(assignments).isEmpty();
    }
}
