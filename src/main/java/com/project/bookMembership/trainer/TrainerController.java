package com.project.bookMembership.trainer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/api/v1/trainer")
@RequiredArgsConstructor
public class TrainerController {
    
    private final TrainerService trainerService;
   

    @PostMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody TrainerRequest trainerRequest) {
        
        trainerService.save(trainerRequest); 

        return ResponseEntity.ok("Trainer added successfully");
    }

}
