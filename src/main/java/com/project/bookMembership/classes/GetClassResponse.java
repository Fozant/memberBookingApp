package com.project.bookMembership.classes;

import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GetClassResponse {

    private int capacity;
   private Long idClass;

    private Date classDate;
    private LocalTime classTime;
    private Long classCapasity;
    
}
