package com.education.finalorm.config;

import com.education.finalorm.entity.Category;
import com.education.finalorm.entity.Tag;
import com.education.finalorm.entity.Teacher;
import com.education.finalorm.repository.CategoryRepository;
import com.education.finalorm.repository.TagRepository;
import com.education.finalorm.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.findByNameIgnoreCase("Programming").isEmpty()) {
            Category category = new Category();
            category.setName("Programming");
            categoryRepository.save(category);
        }


        if (tagRepository.findByNameIgnoreCase("Java").isEmpty()) {
            Tag tag = new Tag();
            tag.setName("Java");
            tag.setSlug("java");
            tagRepository.save(tag);
        }

        if (teacherRepository.findByEmail("teacher@example.com").isEmpty()) {
            Teacher teacher = new Teacher();
            teacher.setFirstName("Вася");
            teacher.setLastName("Петров");
            teacher.setEmail("teacher@example.com");
            teacher.setRole(UserRole.TEACHER);
            teacher.setAcademicTitle("Professor");
            teacherRepository.save(teacher);
        }
    }
}
