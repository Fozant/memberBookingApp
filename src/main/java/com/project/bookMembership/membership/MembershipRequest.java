package com.project.bookMembership.membership;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipRequest {
    
    private String token;

    private Date startDate;
    private Date endDate;
    private Long price;
    private Long duration;

    
}

