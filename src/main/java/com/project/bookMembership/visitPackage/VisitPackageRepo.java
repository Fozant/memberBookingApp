package com.project.bookMembership.visitPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitPackageRepo extends JpaRepository<VisitPackage,Long> {

    
} 