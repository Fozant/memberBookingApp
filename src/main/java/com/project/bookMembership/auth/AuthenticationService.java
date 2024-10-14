package com.project.bookMembership.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.trainer.Trainer;
import com.project.bookMembership.trainer.TrainerRequest;
import com.project.bookMembership.trainer.TrainerService;
import com.project.bookMembership.user.Role;
import com.project.bookMembership.user.User;
import com.project.bookMembership.user.UserRepo;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TrainerService trainerService;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }
        Date currentdate = new Date();
        var user = User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .pNumber(request.getpNumber())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.ROLE_USER)
            .registrationDate(currentdate)
            .build();

        repo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
    public AuthenticationResponse registerTrainer(TrainerRequest request) {
    Date currentdate = new Date();

    
    Trainer trainer = trainerService.findTrainerById(request.getIdTrainer());

    var user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .pNumber(request.getPNumber())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ROLE_TRAINER)
        .registrationDate(currentdate)
        .idTrainer(trainer)  
        .build();

    repo.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
}
public AuthenticationResponse authenticate(AuthenticationRequest request) {
    try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        var user = repo.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .role(user.getRole())
            .idUser(user.getIdUser())
            .build();
    } catch (BadCredentialsException ex) {
        throw new RuntimeException("Wrong email or password"); // Customize this message as needed
    } catch (RuntimeException ex) {
        throw new RuntimeException("Authentication failed: " + ex.getMessage());
    }
}}