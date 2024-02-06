package com.project.bookMembership.classes;

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
