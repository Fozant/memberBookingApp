package com.project.bookMembership.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    private final TrainingClassRepo trainingClassRepo;
    private final ClassTrainerDetailRepo classTrainerRepo;

    @Autowired
    public ClassServiceImpl(TrainingClassRepo trainingClassRepo, ClassTrainerDetailRepo classTrainerRepo) {
        this.trainingClassRepo = trainingClassRepo;
        this.classTrainerRepo = classTrainerRepo; 
    }


    @Override
    public TrainingClass save(ClassRequest request) {
    
        
        var trainingClass = TrainingClass.builder()
            .classDate(request.getClassDate())
            .classTime(request.getClassTime())
            .classCapasity(request.getClassCapasity())
            .build();
    
        
        TrainingClass savedClass = trainingClassRepo.save(trainingClass);
    
     
        
    
      
        var classTrainerDetail = ClassTrainerDetail.builder()
            .idTrainer(request.getIdTrainer())  
            .idClass(savedClass.getIdClass())  
            .build();
    
     
            classTrainerRepo.save(classTrainerDetail);  
    
       
        return savedClass;
    }

    @Override
    public List<TrainingClass> getTrainingClass() {
           List<TrainingClass> classes = trainingClassRepo.findAll();
    
        if (classes.isEmpty()) {
            throw new RuntimeException("No training classes found");
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
