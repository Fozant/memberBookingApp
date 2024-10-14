package com.project.bookMembership.user;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.bookMembership.trainer.Trainer;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "user")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    private String pNumber;
    private String name;
    private String email;
    private Date registrationDate;
    private String password;
    
    
    @OneToOne
    @JoinColumn(name = "id_trainer", referencedColumnName = "idTrainer", nullable = true)
    private Trainer idTrainer;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Role getRole() {
        return this.role;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    public static class UserBuilder {
        public UserBuilder idTrainer(Trainer trainer) {
            this.idTrainer = trainer;
            return this;
        }
    }

    @Override
    public String getUsername() {
        
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        return true;
    }

    @Override
    public String getPassword() {
       
        return password;
    }

  

}
