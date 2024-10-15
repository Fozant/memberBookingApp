package com.project.bookMembership.classes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.trainer.Trainer;
import com.project.bookMembership.trainer.TrainerRepo;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ClassServiceImpl implements ClassService {

    private final TrainingClassRepo trainingClassRepo;
    private final ClassTrainerDetailRepo classTrainerRepo;
    private final TrainerRepo trainerRepo;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    @Autowired
    public ClassServiceImpl(TrainingClassRepo trainingClassRepo, ClassTrainerDetailRepo classTrainerRepo,TrainerRepo trainerRepo, JwtService jwtService,UserRepo userRepo) {
        this.trainingClassRepo = trainingClassRepo;
        this.classTrainerRepo = classTrainerRepo; 
        this.trainerRepo = trainerRepo; 
        this.jwtService = jwtService; 
        this.userRepo = userRepo; 
    }


    @Override
    public TrainingClass save(ClassRequest request) {
    
        var trainingClass = TrainingClass.builder()
            .classDate(request.getClassDate())
            .classTime(request.getClassTime())
            .classCapasity(request.getClassCapasity())
            .className(request.getClassName())
            .classRequirement(request.getClassRequirement())
            .build();
    
        
        TrainingClass savedClass = trainingClassRepo.save(trainingClass);
        
      
        Trainer trainer = trainerRepo.findById(request.getIdTrainer())
        .orElseThrow(() -> new RuntimeException("Trainer not found"));


        var classTrainerDetail = ClassTrainerDetail.builder()
            .idTrainer(trainer)  
            .idClass(savedClass)  
            .build();
    
     
            classTrainerRepo.save(classTrainerDetail);  
    
       
        return savedClass;
    }

    @Override
    public List<TrainingClass> getTrainingClass() {

           List<TrainingClass> classes = trainingClassRepo.findAll();
    
        if (classes.isEmpty()) {
           return new ArrayList<>();
        }
    
        return classes;
    }


    @Override
    public List<TrainingClass> getTrainingClassById(Long id) {
        
        Optional<TrainingClass> trainingClass = trainingClassRepo.findById(id);
    
        if (trainingClass.isEmpty()) {
           return new ArrayList<>();
        }
    
        return List.of(trainingClass.get());
    }


    @Override
    public List<TrainingClass> getTrainingClassByDate(Date date) {
        
        List<TrainingClass> trainingClass = trainingClassRepo.findByClassDate(date);
    
        if (trainingClass.isEmpty()) {
           return new ArrayList<>();
        }
    
        return trainingClass;
    }

    @Override
    public List<TrainingClass> getClassHistory(ClassHistoryRequest classHistoryRequest) {
        String email = jwtService.extractUsername(classHistoryRequest.getToken());
        
        // Fetch the user as an Optional
        Optional<User> optionalUser = userRepo.findByEmail(email);
        

        if (optionalUser.isPresent()) {
            User user = optionalUser.get(); 
            
            List<TrainingClass> trainingClasses = trainingClassRepo.findByUserId(user.getIdUser());
            return trainingClasses;
        } else {

            return new ArrayList<>(); 
        }
    }
   

    
}
