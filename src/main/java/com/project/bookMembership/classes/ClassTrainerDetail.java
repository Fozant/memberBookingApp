package com.project.bookMembership.classes;

import com.project.bookMembership.trainer.Trainer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "ClassTrainerDetail")
public class ClassTrainerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "idTrainer")
    private Trainer idTrainer;

    @OneToOne
    @JoinColumn(name = "class_id", referencedColumnName = "idClass")
    private TrainingClass idClass;
}
