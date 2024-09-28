package com.project.bookMembership.trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TrainerRequest {
    private long idTrainer;
    private String trainerName;
    private String trainerDescription;
}
