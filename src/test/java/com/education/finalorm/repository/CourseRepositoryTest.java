package com.education.finalorm.repository;

import com.education.finalorm.entity.Category;
import com.education.finalorm.entity.Course;
import com.education.finalorm.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldFindCoursesByTeacher() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Вася");
        teacher.setLastName("Петров");
        teacher.setEmail("vasia@example.com");
        teacher.setRole(UserRole.TEACHER);
        entityManager.persistAndFlush(teacher);

        Category category = new Category();
        category.setName("Programming");
        entityManager.persistAndFlush(category);

        Course course1 = new Course();
        course1.setTitle("Java Basics");
        course1.setTeacher(teacher);
        course1.setCategory(category);
        entityManager.persistAndFlush(course1);

        Course course2 = new Course();
        course2.setTitle("Advanced Java");
        course2.setTeacher(teacher);
        course2.setCategory(category);
        entityManager.persistAndFlush(course2);

        entityManager.clear();


        var courses = courseRepository.findByTeacherId(teacher.getId());


        assertThat(courses).hasSize(2);
        assertThat(courses.get(0).getTitle()).isEqualTo("Java Basics");
    }
}
