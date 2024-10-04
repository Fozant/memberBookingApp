package com.project.bookMembership.visitPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.transaction.Transaction;
import com.project.bookMembership.transaction.TransactionService;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;

@Service

public class VisitPackageServiceImpl implements VisitPackageService{

    private final VisitPackageRepo visitPackageRepo;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final TransactionService transactionService;


    @Autowired
    public VisitPackageServiceImpl(VisitPackageRepo visitPackageRepo,
                                UserRepo userRepo,
                                JwtService jwtService,
                                TransactionService transactionService) {
        this.visitPackageRepo = visitPackageRepo;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.transactionService = transactionService;
    }

    @Override
    public VisitPackage save(VisitPackageRequest visitPackageRequest) {

        String emailz = jwtService.extractUsername(visitPackageRequest.getToken());
        User user = userRepo.findByEmail(emailz)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = Transaction.builder()
            .visitStartDate(visitPackageRequest.getVisitStartDate())
            .visitEndDate(visitPackageRequest.getVisitEndDate())
            // .paymentType(visitPackageRequest.getPaymentType())
            .paymentMethod(visitPackageRequest.getPaymentMethod())
            .paymentStatus(visitPackageRequest.getPaymentStatus())
            .transactionPrice(visitPackageRequest.getTransactionPrice())
            .build();



            transactionService.save(transaction);

          VisitPackage vpPackage = VisitPackage.builder()
            .user(user)
            .transaction(transaction)
            .startDate(visitPackageRequest.getStartDate())
            .endDate(visitPackageRequest.getEndDate())
            .price(visitPackageRequest.getPrice())
            .visitNumber(visitPackageRequest.getVisitNumber())
            .visitUsed(visitPackageRequest.getVisitUsed())
            .build();

   
    VisitPackage savedVPackage = visitPackageRepo.save(vpPackage);


    transaction.setVisitPackage(vpPackage);

  
    transactionService.save(transaction);

 
    return savedVPackage;
    }
    
}
