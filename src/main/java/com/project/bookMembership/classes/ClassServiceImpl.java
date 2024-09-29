package com.project.bookMembership.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookMembership.trainer.Trainer;
import com.project.bookMembership.trainer.TrainerRepo;
import java.util.ArrayList;

@Service
public class ClassServiceImpl implements ClassService {

    private final TrainingClassRepo trainingClassRepo;
    private final ClassTrainerDetailRepo classTrainerRepo;
    private final TrainerRepo trainerRepo;

    @Autowired
    public ClassServiceImpl(TrainingClassRepo trainingClassRepo, ClassTrainerDetailRepo classTrainerRepo,TrainerRepo trainerRepo) {
        this.trainingClassRepo = trainingClassRepo;
        this.classTrainerRepo = classTrainerRepo; 
        this.trainerRepo = trainerRepo; 
    }


    @Override
    public TrainingClass save(ClassRequest request) {
    
        var trainingClass = TrainingClass.builder()
            .classDate(request.getClassDate())
            .classTime(request.getClassTime())
            .classCapasity(request.getClassCapasity())
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
   

    /*

     *  public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .pNumber(request.getpNumber())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();

        repo.save(user);
     */
    
}
