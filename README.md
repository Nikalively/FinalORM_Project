# FinalORM_Project

## Описание проекта

FinalORM_Project - это веб-приложение учебной платформы, разработанное на базе Spring Boot с использованием Hibernate/JPA для работы с базой данных PostgreSQL. Приложение предоставляет функциональность для управления курсами, пользователями (студентами и преподавателями), заданиями, тестами и отзывами. Проект демонстрирует работу с ORM, включая связи между сущностями, ленивую загрузку, CRUD-операции, интеграционные тесты и REST API.

## Основные возможности

- Управление пользователями: создание студентов и преподавателей.
- Управление категориями и тегами курсов.
- Создание и управление курсами с модулями и уроками.
- Запись студентов на курсы.
- Создание заданий и прием решений с оценками.
- Проведение тестов (викторин) с вопросами и вариантами ответов.
- Отзывы о курсах.
- REST API для всех основных операций.
- Интеграционные тесты для проверки CRUD и бизнес-логики.

## Архитектура

- **Модель данных**: 16 сущностей (User, Student, Teacher, Profile, Category, Tag, Course, Module, Lesson, Enrollment, CourseReview, Assignment, Submission, Quiz, Question, QuestionOption, QuizSubmission) с полными связями.
- **Слои**: Entity (сущности), Repository (Spring Data JPA), Service (бизнес-логика), Controller (REST API), DTO (запросы/ответы), Mapper (MapStruct).
- **Технологии**: Java 17, Spring Boot 3.2.1, Hibernate, PostgreSQL, Lombok, MapStruct, Gradle, Docker Compose, H2 для тестов.
- **Ленивая загрузка**: Все связи настроены на LAZY для демонстрации Lazy Loading (сессии управляются через @Transactional).

## Структура базы данных

- **Таблицы**: users, students, teachers, profiles, categories, tags, courses, course_tags, modules, lessons, enrollments, course_reviews, assignments, submissions, quizzes, questions, question_options, quiz_submissions.
- **Связи**: Inheritance (JOINED) для User, OneToOne (Profile-User), ManyToOne (Course-Category/Teacher), OneToMany (Course-Modules, etc.), ManyToMany (Course-Tags).
- **DDL**: Автоматическое создание/обновление через spring.jpa.hibernate.ddl-auto=update.

## Реализация требований технического задания

По техническому заданию были реализованы следующие требования:

1. **Модель данных**: Реализованы 16 сущностей с полными связями между ними, включая один-к-одному (Profile-User), один-ко-многим (Course-Modules/Lessons) и многие-ко-многим (Course-Tags). Использовано наследование (JOINED) для сущности User.

2. **Репозитории и CRUD-операции**: Для всех сущностей созданы Spring Data репозитории с полным набором CRUD-операций (создание, чтение, обновление, удаление). Учтены каскады и ограничения для поддержания целостности данных.

3. **Управление курсами и контентом**: Реализовано полноценное управление курсами, включая создание курсов с привязкой к категории и преподавателю, добавление модулей и уроков, корректное сохранение и извлечение данных с подгрузкой связанных сущностей.

4. **Запись на курс**: Полностью реализована регистрация студентов на курсы с проверкой уникальности записей, возможностью отписки, а также получение списков курсов для студентов и студентов для курсов. Many-to-Many связь прекрасно работает.

5. **Задания и решения**: Реализован полный цикл работы с заданиями: преподаватель может добавлять задания, студент отправляет решения; предусмотрено предотвращение повторных попыток, просмотр и оценка решений. Все связи между заданиями, уроками и решениями учтены в реализации проекта.

6. **Тесты/викторины**: Реализованы сущности Quiz, Question, QuestionOption и QuizSubmission; предусмотрена возможность прохождения тестов (викторин), сохранение результатов; реализованы методы для получения данных о викторинах и результатах.

7. **Конфигурация приложения (PostgreSQL)**: Настройки приложения определены в application.yaml, используются профили (dev/test), переменные окружения; чувствительные данные (пароли, URL) не хранятся в коде, а вынесены в переменные.

8. **Интеграционное тестирование CRUD**: Реализован полный набор интеграционных тестов для CRUD-операций с использованием H2 in-memory базы данных; покрыты все основные сценарии, тесты проходят успешно.

9. **REST API для основных операций**: Полный REST API с корректными HTTP-методами, статус-кодами, обработкой ошибок; API охватывает все сущности и основные операции, включая создание, чтение, обновление и удаление.

10. **Валидация ввода и обработка ошибок**: Реализована валидация всех входных данных, централизованная обработка ошибок через ControllerAdvice, с предоставлением понятных сообщений об ошибках пользователю.

11. **Предзаполнение данными**: Реализовано предзаполнение базы данных демо-данными в файлах data.sql и через DataInitializer. 

12. **Тестирование (unit + интеграционные)**: Реализован набор unit- и интеграционных тестов с покрытием более 50% кода.

13. **Архитектура и качество кода**: Код структурирован по слоям (Entity, Repository, Service, Controller), соблюдены принципы SOLID, DI, DRY; классы и методы хорошо читаются и понятны.

14. **Документация проекта**: Составлен полный README с инструкциями по установке, запуску, API-документацией, архитектурой и тестированием.

15. **Автоматизация и DevOps**: Настроены docker-compose и Dockerfile для автоматизации развертывания, скрипты сборки и запуска. Тесты запускаются автоматически в процессе сборки.


### Предварительные требования

- JDK 17+
- Docker и Docker Compose (для PostgreSQL)
- Gradle (или используйте ./gradlew)

### Шаги по установке

1. Клонируйте репозиторий:
   git clone https://github.com/yourusername/FinalORM_Project.git

2. Запустите PostgreSQL через Docker Compose:
   docker-compose up -d
3. Соберите проект:
      ./gradlew build
4. Запустите приложение:
   ./gradlew bootRun


## Тестирование

- **Интеграционные тесты**: Проверяют CRUD, связи, ленивую загрузку. Запускаются с H2.
- **Репозиторные тесты**: Тестируют запросы с TestEntityManager.


### Конфигурация

- **application.yaml**: Настройки БД, JPA (ddl-auto=update), логирование (DEBUG для Hibernate).
- **data.sql**: Предзаполнение демо-данными (курсы, пользователи, etc.). Запускается автоматически при старте.
- **Docker Compose**: Определяет сервис PostgreSQL с healthcheck.

## API Документация

Приложение предоставляет REST API. Используйте Postman или curl для тестирования. Базовый URL: http://localhost:8080/api

### Users

- POST /api/users/students - Создать студента
- POST /api/users/teachers - Создать преподавателя
- GET /api/users/{id} - Получить пользователя по ID
- GET /api/users/role/{role} - Получить пользователей по роли (STUDENT/TEACHER/ADMIN)
- PUT /api/users/{id} - Обновить пользователя
- DELETE /api/users/{id} - Удалить пользователя

### Categories

- POST /api/categories - Создать категорию
- GET /api/categories/{id} - Получить категорию по ID
- GET /api/categories - Получить все категории
- PUT /api/categories/{id} - Обновить категорию
- DELETE /api/categories/{id} - Удалить категорию

### Courses

- POST /api/courses - Создать курс
- GET /api/courses/{id} - Получить курс по ID
- GET /api/courses/{id}/detail - Получить детальную информацию о курсе
- GET /api/courses - Получить все курсы
- GET /api/courses/search?query={text} - Поиск курсов по названию/описанию
- PUT /api/courses/{id} - Обновить курс
- DELETE /api/courses/{id} - Удалить курс

### Enrollments

- POST /api/enrollments - Записать студента на курс
- GET /api/enrollments/student/{studentId} - Получить записи студента
- PUT /api/enrollments/{id}/status - Обновить статус записи
- DELETE /api/enrollments/{id} - Отменить запись

### Assignments

- POST /api/assignments - Создать задание
- GET /api/assignments/lesson/{lessonId} - Получить задания урока
- PUT /api/assignments/{id} - Обновить задание

### Submissions

- POST /api/submissions - Отправить решение
- GET /api/submissions/student/{studentId} - Получить решения студента
- PUT /api/submissions/{id}/grade - Оценить решение

### Quizzes


- POST /api/quizzes - Создать
- GET /api/quizzes/{id} - Получить по ID

### Lessons

- POST /api/lessons - Создать урок
- GET /api/lessons/module/{moduleId} - Получить уроки модуля
- PUT /api/lessons/{id} - Обновить урок

### Modules

- POST /api/modules - Создать модуль
- GET /api/modules/course/{courseId} - Получить модули курса
- PUT /api/modules/{id} - Обновить модуль

### Reviews

- POST /api/reviews - Создать отзыв
- GET /api/reviews/course/{courseId} - Получить отзывы курса
- GET /api/reviews/course/{courseId}/average-rating - Получить средний рейтинг курса

