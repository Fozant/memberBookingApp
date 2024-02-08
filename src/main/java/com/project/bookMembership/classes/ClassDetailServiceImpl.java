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

//     @Override
// public ClassDetail addClassDetail(ClassDetailRequest classDetailRequest) {
//     // Extract username from the provided token
//     String username = jwtService.extractUsername(classDetailRequest.getToken());

//     // Retrieve the TrainingClass using the provided idClass
//     Long idClass = classDetailRequest.getIdClass();
//     TrainingClass trainingClass = classDetailRepository.findTrainingClassById(idClass)
//             .orElseThrow(() -> new RuntimeException("TrainingClass not found"));

//     // Retrieve the User using the extracted username
//     User user = userRepo.findByEmail(username)
//             .orElseThrow(() -> new RuntimeException("User not found"));

//     var classDetail = ClassDetail.builder()
//             .idUser(user) 
//             .idClass(trainingClass)
//             .build();

//     return classDetailRepository.save(classDetail);
// }


@Override
public ClassDetail addClassDetail(ClassDetailRequest classDetailRequest) {
       

       String emailz =(jwtService.extractUsername(classDetailRequest.getToken()));
       User user = userRepo.findByEmail(emailz)
       .orElseThrow(() -> new RuntimeException("User not found"));

// Check if the training class exists
        Long idTrainingclass = classDetailRequest.getIdClass();
TrainingClass trainingClass = trainingClassRepo.findById(idTrainingclass)
       .orElseThrow(() -> new RuntimeException("Training class not found"));


var classDetail = ClassDetail.builder()
            .idUser(user) 
            .idClass(trainingClass)
            .build();

    return classDetailRepository.save(classDetail);

}
// @Override
// public TrainingClass save(ClassRequest request) {
//     var trainingClass = TrainingClass.builder()
//         .classDate(request.getClassDate())
//         .classTime(request.getClassTime())
//         .classCapasity(request.getClassCapasity())
       
//         .build();
      
//     return trainingClassRepo.save(trainingClass);
// }

}