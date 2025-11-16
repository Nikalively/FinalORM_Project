package com.education.finalorm.repository;

import com.education.finalorm.entity.Enrollment;
import com.education.finalorm.entity.Student;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.enums.EnrollmentStatus;
import com.education.finalorm.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EnrollmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void shouldFindEnrollmentByStudentAndCourse() {

        Student student = new Student();
        student.setFirstName("Иван");
        student.setLastName("Иванов");
        student.setEmail("ivanov@example.com");
        student.setRole(UserRole.STUDENT);
        student.setBirthDate(java.time.LocalDate.now());
        student.setStudentNo("ST001");
        entityManager.persistAndFlush(student);

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

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        entityManager.persistAndFlush(enrollment);

        entityManager.clear();


        Optional<Enrollment> found = enrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId());


        assertThat(found).isPresent();
        assertThat(found.get().getStatus()).isEqualTo(EnrollmentStatus.ACTIVE);
    }

    @Test
    void shouldFindEnrollmentsByStudent() {

        Student student = new Student();
        student.setFirstName("Иван");
        student.setLastName("Иванов");
        student.setEmail("ivanov@example.com");
        student.setRole(UserRole.STUDENT);
        student.setBirthDate(java.time.LocalDate.now());
        student.setStudentNo("ST001");
        entityManager.persistAndFlush(student);

        Teacher teacher = new Teacher();
        teacher.setFirstName("Вася");
        teacher.setLastName("Петров");
        teacher.setEmail("vasia@example.com");
        teacher.setRole(UserRole.TEACHER);
        teacher.setAcademicTitle("Professor");
        entityManager.persistAndFlush(teacher);

        Course course1 = new Course();
        course1.setTitle("Java Basics");
        course1.setTeacher(teacher);
        entityManager.persistAndFlush(course1);

        Course course2 = new Course();
        course2.setTitle("Advanced Java");
        course2.setTeacher(teacher);
        entityManager.persistAndFlush(course2);

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setStudent(student);
        enrollment1.setCourse(course1);
        enrollment1.setEnrollDate(LocalDateTime.now());
        enrollment1.setStatus(EnrollmentStatus.ACTIVE);
        entityManager.persistAndFlush(enrollment1);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setStudent(student);
        enrollment2.setCourse(course2);
        enrollment2.setEnrollDate(LocalDateTime.now());
        enrollment2.setStatus(EnrollmentStatus.ACTIVE);
        entityManager.persistAndFlush(enrollment2);

        entityManager.clear();


        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(student.getId());


        assertThat(enrollments).hasSize(2);
    }
}
