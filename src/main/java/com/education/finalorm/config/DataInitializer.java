package com.education.finalorm.config;

import com.education.finalorm.entity.*;
import com.education.finalorm.enums.EnrollmentStatus;
import com.education.finalorm.enums.SubmissionStatus;
import com.education.finalorm.enums.UserRole;
import com.education.finalorm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final CourseReviewRepository courseReviewRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Student student1 = new Student();
            student1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"));
            student1.setFirstName("Вася");
            student1.setLastName("Петров");
            student1.setEmail("vasia@example.com");
            student1.setDateOfBirth(LocalDate.of(1990, 1, 1));
            student1.setStudentNo("ST001");
            userRepository.save(student1);

            Teacher teacher1 = new Teacher();
            teacher1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"));
            teacher1.setFirstName("Иван");
            teacher1.setLastName("Иванов");
            teacher1.setEmail("ivanov@example.com");
            teacher1.setDateOfBirth(LocalDate.of(1985, 5, 15));
            teacher1.setTeacherNo("T001");
            userRepository.save(teacher1);

            Student student2 = new Student();
            student2.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440003"));
            student2.setFirstName("Петр");
            student2.setLastName("Сидоров");
            student2.setEmail("sidorov@example.com");
            student2.setDateOfBirth(LocalDate.of(1992, 10, 20));
            student2.setStudentNo("ST002");
            userRepository.save(student2);

            Profile profile1 = new Profile();
            profile1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440004"));
            profile1.setBio("Student bio");
            profile1.setUser(student1);
            profileRepository.save(profile1);

            Profile profile2 = new Profile();
            profile2.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440005"));
            profile2.setBio("Teacher bio");
            profile2.setUser(teacher1);
            profileRepository.save(profile2);

            Category category1 = new Category();
            category1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440006"));
            category1.setName("Programming");
            category1.setDescription("Courses on programming languages");
            categoryRepository.save(category1);

            Tag tag1 = new Tag();
            tag1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440007"));
            tag1.setName("Java");
            tagRepository.save(tag1);

            Tag tag2 = new Tag();
            tag2.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440008"));
            tag2.setName("Spring");
            tagRepository.save(tag2);

            Course course1 = new Course();
            course1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440009"));
            course1.setTitle("Java Basics");
            course1.setDescription("Learn Java fundamentals");
            course1.setPrice(BigDecimal.valueOf(99.99));
            course1.setCategory(category1);
            course1.setTeacher(teacher1);
            course1.setTags(List.of(tag1, tag2));
            courseRepository.save(course1);

            Module module1 = new Module();
            module1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440010"));
            module1.setTitle("Module 1");
            module1.setDescription("Introduction");
            module1.setCourse(course1);
            moduleRepository.save(module1);

            Lesson lesson1 = new Lesson();
            lesson1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440011"));
            lesson1.setTitle("Lesson 1");
            lesson1.setContent("Content here");
            lesson1.setModule(module1);
            lessonRepository.save(lesson1);

            Enrollment enrollment1 = new Enrollment();
            enrollment1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440012"));
            enrollment1.setStudent(student1);
            enrollment1.setCourse(course1);
            enrollment1.setStatus(EnrollmentStatus.ACTIVE);
            enrollmentRepository.save(enrollment1);

            Assignment assignment1 = new Assignment();
            assignment1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440013"));
            assignment1.setTitle("Assignment 1");
            assignment1.setDescription("Solve problem");
            assignment1.setLesson(lesson1);
            assignmentRepository.save(assignment1);

            Submission submission1 = new Submission();
            submission1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440014"));
            submission1.setContent("Solution content");
            submission1.setStudent(student1);
            submission1.setAssignment(assignment1);
            submission1.setStatus(SubmissionStatus.PENDING);
            submissionRepository.save(submission1);

            Quiz quiz1 = new Quiz();
            quiz1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440015"));
            quiz1.setTitle("Quiz 1");
            quiz1.setModule(module1);
            quizRepository.save(quiz1);

            Question question1 = new Question();
            question1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440016"));
            question1.setQuestionText("What is Java?");
            question1.setType(com.education.finalorm.enums.QuestionType.MULTIPLE_CHOICE);
            question1.setQuiz(quiz1);
            questionRepository.save(question1);

            QuestionOption option1 = new QuestionOption();
            option1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440017"));
            option1.setOptionText("A language");
            option1.setCorrect(true);
            option1.setQuestion(question1);
            questionOptionRepository.save(option1);

            CourseReview review1 = new CourseReview();
            review1.setId(UUID.fromString("550e8400-e29b-41d4-a716-446655440018"));
            review1.setRating(5);
            review1.setComment("Great course!");
            review1.setStudent(student1);
            review1.setCourse(course1);
            courseReviewRepository.save(review1);
        }
    }
}
