package com.project.bookMembership.classes;

import java.util.List;

import org.springframework.stereotype.Service;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;
import com.project.bookMembership.visitPackage.VisitPackage;
import com.project.bookMembership.visitPackage.VisitPackageRepo;
import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.membership.Membership;
import com.project.bookMembership.membership.MembershipRepo;

@Service
public class ClassDetailServiceImpl implements ClassDetailService {

    private final ClassDetailRepository classDetailRepository;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final TrainingClassRepo trainingClassRepo;
    private final MembershipRepo membershipRepo;
    private final VisitPackageRepo visitRepo;

    public ClassDetailServiceImpl(
            ClassDetailRepository classDetailRepository,
            UserRepo userRepo,
            JwtService jwtService,
            TrainingClassRepo trainingClassRepo,
            MembershipRepo membershipRepo,
            VisitPackageRepo visitRepo) {
        this.classDetailRepository = classDetailRepository;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.trainingClassRepo = trainingClassRepo;
        this.membershipRepo = membershipRepo;
        this.visitRepo = visitRepo;
    }

    @Override
    public ClassDetail book(ClassDetailRequest classDetailRequest) {
        String emailz = jwtService.extractUsername(classDetailRequest.getToken());
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
    

      
    if (classDetailRequest.getType().equals("member")) {
        List<Membership> memberships = membershipRepo.findByUserId(user.getIdUser());
        boolean hasActiveMembership = memberships.stream()
            .anyMatch(membership -> 
                !membership.getStartDate().after(trainingClass.getClassDate()) &&
                !membership.getEndDate().before(trainingClass.getClassDate())
            );

        if (!hasActiveMembership) {
            throw new RuntimeException("User does not have an active membership for this class date.");
        }
    }

      
        else if (classDetailRequest.getType().equals("visit")) {
            List<VisitPackage> visitPackages =  visitRepo.findByUserId(user.getIdUser());
           
            boolean hasAvailableVisit = false;
            for (VisitPackage visitPackage : visitPackages) {
                if (visitPackage.getVisitNumber() > visitPackage.getVisitUsed()) {
                    hasAvailableVisit = true;
                    visitPackage.incrementUsedVisits(); 
                    visitRepo.save(visitPackage); 
                    break; 
                }
            }
        
            if (!hasAvailableVisit) {
                throw new RuntimeException("No visits available");
            }
        } 
    
       
        var classDetail = ClassDetail.builder()
                .idUser(user)
                .idClass(trainingClass)
                .build();
    
        return classDetailRepository.save(classDetail);
    }


}