package com.project.bookMembership.classes;

import java.util.List;
import java.util.Date;


public interface ClassService {
    TrainingClass save(ClassRequest request);
    List<TrainingClass> getTrainingClass();
    List<TrainingClass> getTrainingClassById(Long id);
    List<TrainingClass> getTrainingClassByDate(Date date);
    List<TrainingClass> getClassHistory(ClassHistoryRequest classHistoryRequest);
    
    
} 