package com.project.bookMembership.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.transaction.Transaction;
import com.project.bookMembership.transaction.TransactionService;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepo membershipRepo;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final TransactionService transactionService;

    @Autowired
    public MembershipServiceImpl(MembershipRepo membershipRepo,
                                UserRepo userRepo,
                                JwtService jwtService,
                                TransactionService transactionService) {
        this.membershipRepo = membershipRepo;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.transactionService = transactionService;
    }

    @Override
    public Membership save(MembershipRequest membershipRequest) {
        String emailz = jwtService.extractUsername(membershipRequest.getToken());
        User user = userRepo.findByEmail(emailz)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        // Create Transaction entity
        Transaction transaction = Transaction.builder()
                .visitStartDate(membershipRequest.getVisitStartDate())
                .visitEndDate(membershipRequest.getVisitEndDate())
                .paymentType(membershipRequest.getPaymentType())
                .paymentMethod(membershipRequest.getPaymentMethod())
                .paymentStatus(membershipRequest.getPaymentStatus())
                .transactionPrice(membershipRequest.getTransactionPrice())
                .build();
    
        // Save Transaction entity
        transactionService.save(transaction);
    
        // Create Membership entity and set the saved Transaction
        Membership membership = Membership.builder()
                .user(user)
                .transaction(transaction)  // Set the saved Transaction
                .startDate(membershipRequest.getStartDate())
                .endDate(membershipRequest.getEndDate())
                .price(membershipRequest.getPrice())
                .duration(membershipRequest.getDuration())
                .build();
    
        // Save Membership entity
        return membershipRepo.save(membership);
    }
}
