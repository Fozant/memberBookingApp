package com.project.bookMembership.trainer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookMembership.classes.TrainingClass;
import com.project.bookMembership.classes.TrainingClassRepo;
import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;

import java.util.ArrayList;
import java.util.List;
@Service
public class TrainerServiceImpl implements TrainerService{

    private final TrainerRepo trainerRepo;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final TrainingClassRepo trainingClassRepo;


    @Autowired
    public TrainerServiceImpl(TrainerRepo trainerRepo,JwtService jwtService,UserRepo userRepo, TrainingClassRepo trainingClassRepo) {
        this.trainerRepo = trainerRepo;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.trainingClassRepo = trainingClassRepo;
     
    }


    @Override
    public Trainer save(TrainerRequest request) {
     
        
        Trainer  trainer = Trainer.builder()
            .trainerName(request.getTrainerName())
            .trainerDescription(request.getTrainerDescription())
            .build();
    
            
            return trainerRepo.save(trainer); 
    }


    @Override
    public List<TrainingClass> getTrainerSchedule(TrainerScheduleRequest trainerScheduleRequest) {
        String email = jwtService.extractUsername(trainerScheduleRequest.getToken());
        
        Optional<User> optionalUser = userRepo.findByEmail(email);
    
        if (optionalUser.isPresent()) {
            User user = optionalUser.get(); 
            
            Trainer trainer = user.getIdTrainer(); 
            if (trainer != null) {
               
                Long trainerId = trainer.getIdTrainer();
                List<TrainingClass> trainingClasses = trainingClassRepo.findByTrainerId(trainerId);
                return trainingClasses;
            } else {
               
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public Trainer findTrainerById(Long idTrainer) {
        return trainerRepo.findById(idTrainer)
                .orElseThrow(() -> new RuntimeException("Trainer not found with id: " + idTrainer));
   
    }
    
}
