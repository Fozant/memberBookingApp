package com.transaction;

import java.util.Date;

import com.project.bookMembership.membership.Membership;
import com.project.bookMembership.user.User;
import com.project.bookMembership.visitPackage.VisitPackage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "VisitPackage")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaction;

    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    
    @ManyToOne
    @JoinColumn(name = "Visit_id", nullable = false)
    private VisitPackage visitPackage;



    private String paymentType;
    private String paymentMethod;
    private String paymentStatus;
}
