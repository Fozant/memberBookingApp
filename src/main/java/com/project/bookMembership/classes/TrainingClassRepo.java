package com.project.bookMembership.classes;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingClassRepo extends JpaRepository<TrainingClass,Long>{

    @Query("SELECT t FROM TrainingClass t WHERE DATE(t.classDate) = DATE(:date)")
    
    List<TrainingClass> findByClassDate(@Param("date") Date date);
}