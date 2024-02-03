package com.project.bookMembership.auth;

import com.project.bookMembership.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String pNumber;  
    private Role role;

    public String getpNumber() {
        return this.pNumber;
    }
}
