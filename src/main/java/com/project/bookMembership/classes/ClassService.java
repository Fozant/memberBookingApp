package com.project.bookMembership.classes;

import java.util.List;

public interface ClassService {
    TrainingClass save(ClassRequest request);
    List<TrainingClass> getTrainingClass();
    List<TrainingClass> getTrainingClassById(Long id);
} 