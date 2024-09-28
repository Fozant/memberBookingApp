package com.project.bookMembership.trainer;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer,Long>{
    

}

    

