package com.project.bookMembership.visitPackage;

import java.util.Date;

import com.project.bookMembership.transaction.Transaction;
import com.project.bookMembership.user.User;

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
@Table(name = "VisitPackage")
public class VisitPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVisit;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;


    private Date startDate;
    private Date endDate;
    private Long price;
    private Long visitNumber;
    private long visitUsed;

    public void incrementUsedVisits() {
        this.visitUsed++;
    }

    public void decrementVisitNumber() {
        this.visitNumber--;
    }
}
