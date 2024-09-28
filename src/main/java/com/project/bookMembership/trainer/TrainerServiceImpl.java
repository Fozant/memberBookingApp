package com.project.bookMembership.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService{

    private final TrainerRepo trainerRepo;
  

    @Autowired
    public TrainerServiceImpl(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
     
    }


    @Override
    public Trainer save(TrainerRequest request) {
     
        
        Trainer  trainer = Trainer.builder()
            .trainerName(request.getTrainerName())
            .trainerDescription(request.getTrainerDescription())
            .build();
    
            
            return trainerRepo.save(trainer); 
    }
    
}
