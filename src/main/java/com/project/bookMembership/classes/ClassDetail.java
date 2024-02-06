package com.project.bookMembership.classes;

import com.project.bookMembership.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ClassDetail")
@IdClass(ClassDetailId.class)
public class ClassDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User idUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private TrainingClass idClass;
}
