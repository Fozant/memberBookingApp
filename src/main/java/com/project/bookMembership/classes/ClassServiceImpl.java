package com.project.bookMembership.classes;

import java.util.List;

import org.apache.logging.log4j.util.TriConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    private final TrainingClassRepo trainingClassRepo;
    
     @Autowired
    public ClassServiceImpl(TrainingClassRepo trainingClassRepo) {
        this.trainingClassRepo = trainingClassRepo;
    }

    @Override
    public TrainingClass save(ClassRequest request) {
        var trainingClass = TrainingClass.builder()
            .classDate(request.getClassDate())
            .classTime(request.getClassTime())
            .classCapasity(request.getClassCapasity())
           
            .build();
          
        return trainingClassRepo.save(trainingClass);
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
