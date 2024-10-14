package com.project.bookMembership.classes;

import org.springframework.stereotype.Service;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;
import com.project.bookMembership.config.JwtService;

@Service
public class ClassDetailServiceImpl implements ClassDetailService {

    private final ClassDetailRepository classDetailRepository;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final TrainingClassRepo trainingClassRepo;

    public ClassDetailServiceImpl(
            ClassDetailRepository classDetailRepository,
            UserRepo userRepo,
            JwtService jwtService,
            TrainingClassRepo trainingClassRepo) {
        this.classDetailRepository = classDetailRepository;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.trainingClassRepo = trainingClassRepo;
    }



@Override
public ClassDetail book(ClassDetailRequest classDetailRequest) {
       

       String emailz =(jwtService.extractUsername(classDetailRequest.getToken()));
       User user = userRepo.findByEmail(emailz)
       .orElseThrow(() -> new RuntimeException("User not found"));
  

       Long idTrainingClass = classDetailRequest.getIdClass();
       TrainingClass trainingClass = trainingClassRepo.findById(idTrainingClass)
           .orElseThrow(() -> new RuntimeException("Training class not found"));        


        boolean isAlreadyBooked = classDetailRepository.existsByIdUserAndIdClass(user, trainingClass);
        if (isAlreadyBooked) {
       throw new RuntimeException("User has already booked this class");
           }

        int bookedCount = classDetailRepository.countByIdClass(trainingClass);
        if (bookedCount >= trainingClass.getClassCapasity()) {
            throw new RuntimeException("Class is full");
        }


            var classDetail = ClassDetail.builder()
            .idUser(user) 
            .idClass(trainingClass)
            .build();

    return classDetailRepository.save(classDetail);

}


}