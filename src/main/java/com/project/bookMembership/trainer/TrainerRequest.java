package com.project.bookMembership.trainer;

import com.project.bookMembership.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TrainerRequest {

    private String trainerName;
    private String trainerDescription;


    private String name;
    private String email;
    private String password;
    private String pNumber;  

    private Long idTrainer;

}
