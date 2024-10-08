package com.project.bookMembership.visitPackage;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitPackageRequest {
    private String token;
    
    private Date startDate;
    private Date endDate;
    private Long price;
    private Long visitNumber;
    private long visitUsed;


        //transaction part
    private String paymentType;
    private String paymentMethod;
    private String paymentStatus;
    private Date visitStartDate;
    private Date visitEndDate;
    private Long transactionPrice;
    private MultipartFile buktiTransfer;
}
