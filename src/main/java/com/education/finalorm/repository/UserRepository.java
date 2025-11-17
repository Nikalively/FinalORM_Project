package com.education.finalorm.repository;

import com.education.finalorm.entity.User;
import com.education.finalorm.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE TYPE(u) = :type")
    List<User> findByType(@Param("type") Class<? extends User> type);

    @Query("SELECT u FROM User u WHERE " +
            "(:role = 'STUDENT' AND TYPE(u) = Student) OR " +
            "(:role = 'TEACHER' AND TYPE(u) = Teacher)")
    List<User> findByRole(@Param("role") UserRole role);
}
