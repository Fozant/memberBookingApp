package com.project.bookMembership.classes;


import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TrainingClass")
public class TrainingClass {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClass;
    private String className;
    private String classRequirement;
    private String classNote;


    private Date classDate;
    private LocalTime classTime;
    private Long classCapasity;
 
}
