package com.project.bookMembership.membership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepo extends JpaRepository<Membership,Long>{
    
}
