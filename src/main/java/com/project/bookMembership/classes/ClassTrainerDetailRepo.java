package com.project.bookMembership.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTrainerDetailRepo  extends JpaRepository<ClassTrainerDetail,Long>{

}