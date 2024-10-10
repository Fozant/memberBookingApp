package com.project.bookMembership.trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerScheduleResponse {


       
  
    private Long idClass;
    private String className;
    private String classRequirement;
    private String classNote;


    private Date classDate;
    private LocalTime classTime;
    private Long classCapasity;
 
}


