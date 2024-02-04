package com.project.bookMembership.classes;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookMembership.auth.AuthenticationRequest;
import com.project.bookMembership.auth.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;


    @PostMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody ClassRequest classRequest) {
       
        classService.save(classRequest); 

        return ResponseEntity.ok("Class added successfully");
    }
    
}
