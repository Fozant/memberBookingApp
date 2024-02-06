package com.project.bookMembership.classes;

import com.project.bookMembership.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassDetailRequest {
    
    private Long idUser;
    private Long idClass;
    private String token;
}
