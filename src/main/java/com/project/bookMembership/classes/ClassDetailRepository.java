package com.project.bookMembership.classes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassDetailRepository extends JpaRepository<ClassDetail,Long>{
    Optional<TrainingClass> findTrainingClassById(Long id);
} 