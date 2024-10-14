package com.project.bookMembership.transaction;

import java.util.Date;

import com.project.bookMembership.membership.Membership;
import com.project.bookMembership.visitPackage.VisitPackage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "Transaction")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaction;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "membership_id", nullable = true)
    private Membership membership;

    @ManyToOne
    @JoinColumn(name = "Visit_id", nullable = true)
    private VisitPackage visitPackage;

    private Date visitStartDate;
    private Date visitEndDate;
    // private String paymentType;
    private String paymentMethod;
    private String paymentStatus;
    private Long transactionPrice;

    @Lob
    @Column(name = "bukti_transfer", columnDefinition = "LONGBLOB")
    private byte[] buktiTransfer;
}
