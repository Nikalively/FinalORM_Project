-- Initial data for users (students and teachers)
INSERT INTO users (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date)
VALUES ('550e8400-e29b-41d4-a716-446655440001', NOW(), NOW(), 'Вася', 'Петров', 'vasia@example.com', 'TEACHER', NULL, '1980-01-01');

INSERT INTO teachers (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date, academic_title)
VALUES ('550e8400-e29b-41d4-a716-446655440001', NOW(), NOW(), 'Вася', 'Петров', 'vasia@example.com', 'TEACHER', NULL, '1980-01-01', 'Professor');

INSERT INTO users (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date)
VALUES ('550e8400-e29b-41d4-a716-446655440002', NOW(), NOW(), 'Иван', 'Иванов', 'ivanov@example.com', 'STUDENT', NULL, '1995-05-10');

INSERT INTO students (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date, student_no, status)
VALUES ('550e8400-e29b-41d4-a716-446655440002', NOW(), NOW(), 'Иван', 'Иванов', 'ivanov@example.com', 'STUDENT', NULL, '1995-05-10', 'ST001', 'ACTIVE');

-- Initial data for categories
INSERT INTO categories (id, created_at, updated_at, name)
VALUES ('550e8400-e29b-41d4-a716-446655440003', NOW(), NOW(), 'Programming');

INSERT INTO categories (id, created_at, updated_at, name)
VALUES ('550e8400-e29b-41d4-a716-446655440004', NOW(), NOW(), 'Mathematics');

-- Initial data for tags
INSERT INTO tags (id, created_at, updated_at, name, slug)
VALUES ('550e8400-e29b-41d4-a716-446655440005', NOW(), NOW(), 'Java', 'java');

INSERT INTO tags (id, created_at, updated_at, name, slug)
VALUES ('550e8400-e29b-41d4-a716-446655440006', NOW(), NOW(), 'Spring Boot', 'spring-boot');

-- Initial data for courses
INSERT INTO courses (id, created_at, updated_at, title, description, category_id, teacher_id)
VALUES ('550e8400-e29b-41d4-a716-446655440007', NOW(), NOW(), 'Introduction to Java', 'Learn Java basics',
        '550e8400-e29b-41d4-a716-446655440003',
        '550e8400-e29b-41d4-a716-446655440001');

INSERT INTO course_tags (course_id, tag_id)
VALUES ('550e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440005');

-- Initial data for profiles (optional)
INSERT INTO profiles (id, created_at, updated_at, bio, avatar_url, user_id)
VALUES ('550e8400-e29b-41d4-a716-446655440008', NOW(), NOW(), 'Bio for teacher', 'avatar.jpg', '550e8400-e29b-41d4-a716-446655440001');

INSERT INTO profiles (id, created_at, updated_at, bio, avatar_url, user_id)
VALUES ('550e8400-e29b-41d4-a716-446655440009', NOW(), NOW(), 'Bio for student', 'student-avatar.jpg', '550e8400-e29b-41d4-a716-446655440002');
