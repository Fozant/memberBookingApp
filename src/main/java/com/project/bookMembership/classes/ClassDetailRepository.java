package com.project.bookMembership.classes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookMembership.user.User;


public interface ClassDetailRepository extends JpaRepository<ClassDetail,Long>{
    Optional<TrainingClass> findTrainingClassById(Long id);
    boolean existsByIdUserAndIdClass(User user, TrainingClass trainingClass);
    int countByIdClass(TrainingClass trainingClass);
} 