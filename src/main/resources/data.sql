-- Initial data for users (students and teachers)
INSERT INTO users (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Вася', 'Петров', 'vasia@example.com', 'TEACHER', NULL, '1980-01-01');

INSERT INTO teachers (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date, academic_title)
VALUES ((SELECT id FROM users WHERE email = 'teacher@example.com'), NOW(), NOW(), 'Вася', 'Петрво', 'teacher@example.com', 'TEACHER', NULL, '1980-01-01', 'Professor');

INSERT INTO users (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Иван', 'Иванов', 'ivanov@example.com', 'STUDENT', NULL, '1995-05-10');

INSERT INTO students (id, created_at, updated_at, first_name, last_name, email, role, external_ref, birth_date, student_no, status)
VALUES ((SELECT id FROM users WHERE email = 'student@example.com'), NOW(), NOW(), 'Иван', 'Иванов', 'student@example.com', 'STUDENT', NULL, '1995-05-10', 'ST001', 'ACTIVE');

-- Initial data for categories
INSERT INTO categories (id, created_at, updated_at, name)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Programming');

INSERT INTO categories (id, created_at, updated_at, name)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Mathematics');

-- Initial data for tags
INSERT INTO tags (id, created_at, updated_at, name, slug)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Java', 'java');

INSERT INTO tags (id, created_at, updated_at, name, slug)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Spring Boot', 'spring-boot');

-- Initial data for courses
INSERT INTO courses (id, created_at, updated_at, title, description, category_id, teacher_id)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Introduction to Java', 'Learn Java basics',
        (SELECT id FROM categories WHERE name = 'Programming'),
        (SELECT id FROM teachers WHERE email = 'teacher@example.com'));

INSERT INTO course_tags (course_id, tag_id)
VALUES ((SELECT id FROM courses WHERE title = 'Introduction to Java'), (SELECT id FROM tags WHERE name = 'Java'));

-- Initial data for profiles (optional)
INSERT INTO profiles (id, created_at, updated_at, bio, avatar_url, user_id)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Bio for teacher', 'avatar.jpg', (SELECT id FROM users WHERE email = 'teacher@example.com'));

INSERT INTO profiles (id, created_at, updated_at, bio, avatar_url, user_id)
VALUES (gen_random_uuid(), NOW(), NOW(), 'Bio for student', 'student-avatar.jpg', (SELECT id FROM users WHERE email = 'student@example.com'));
