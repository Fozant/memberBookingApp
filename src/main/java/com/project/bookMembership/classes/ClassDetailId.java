package com.project.bookMembership.classes;

import java.io.Serializable;
import java.util.Objects;

import com.project.bookMembership.user.User;

public class ClassDetailId implements Serializable {

    private User idUser;
    private TrainingClass idClass;

    public ClassDetailId() {
    }

    public ClassDetailId(User idUser, TrainingClass idClass) {
        this.idUser = idUser;
        this.idClass = idClass;
    }

    // Getters and Setters

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public TrainingClass getIdClass() {
        return idClass;
    }

    public void setIdClass(TrainingClass idClass) {
        this.idClass = idClass;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDetailId that = (ClassDetailId) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(idClass, that.idClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idClass);
    }
}