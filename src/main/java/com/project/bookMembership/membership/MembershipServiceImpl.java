package com.project.bookMembership.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.transaction.TransactionService;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;

@Service
public class MembershipServiceImpl implements MembershipService{

    private final MembershipRepo membershipRepo;
    private final JwtService jwtService;
    private final UserRepo userRepo;
  
    
    @Autowired
    public MembershipServiceImpl(MembershipRepo membershipRepo, 
                                UserRepo userRepo, 
                                JwtService jwtService
                                ) {
        this.membershipRepo = membershipRepo;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
      
    }


    @Override
    public Membership save(MembershipRequest membershipRequest) {
        
       String emailz =(jwtService.extractUsername(membershipRequest.getToken()));
       User user = userRepo.findByEmail(emailz)
       .orElseThrow(() -> new RuntimeException("User not found"));

    //    Transaction transaction = createTransaction(request);

         var member = Membership.builder()
            .user(user) 
            .startDate(membershipRequest.getStartDate())
            .endDate(membershipRequest.getEndDate())
            .price(membershipRequest.getPrice())
            .duration(membershipRequest.getDuration())
            
            .build();

         return membershipRepo.save(member);

    }
    // private Transaction createTransaction(MembershipRequest request) {
      

    //     return transactionService.createTransaction(request);
    // }
}
