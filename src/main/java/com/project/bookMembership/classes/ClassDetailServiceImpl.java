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

    public ClassDetailServiceImpl(
            ClassDetailRepository classDetailRepository,
            UserRepo userRepo,
            JwtService jwtService) {
        this.classDetailRepository = classDetailRepository;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    @Override
    public ClassDetail addClassDetail(ClassDetailRequest classDetailRequest) {
        String username = jwtService.extractUsername(classDetailRequest.getToken());
        Long idClass = classDetailRequest.getIdClass();

        // Assuming you have a repository method to find TrainingClass by id
        TrainingClass trainingClass = classDetailRepository.findTrainingClassById(idClass)
                .orElseThrow(() -> new RuntimeException("TrainingClass not found"));

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var classDetailId = new ClassDetailId(user, trainingClass);

        // Use the constructor of the builder to set the id
        var classDetail = ClassDetail.builder()
                .idUser(user)
                .idClass(trainingClass)
                .build();

        return classDetailRepository.save(classDetail);
    }
}
