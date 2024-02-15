package com.project.bookMembership.membership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.bookMembership.classes.TrainingClass;

@Repository
public interface MembershipRepo extends JpaRepository<Membership,Long>{
    
}
