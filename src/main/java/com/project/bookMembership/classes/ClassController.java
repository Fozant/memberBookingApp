package com.project.bookMembership.classes;


import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final ClassDetailService classDetailService;

    @PostMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody ClassRequest classRequest) {
        
        classService.save(classRequest); 

        return ResponseEntity.ok("Class added successfully");
    }

    @PostMapping("/book")
    public ResponseEntity<String> book(@RequestBody ClassDetailRequest classDetailRequest) {
      
      
        classDetailService.book(classDetailRequest);

        return ResponseEntity.ok("book class success");
    }
    
    @GetMapping("/getClasses")
    public ResponseEntity<List<GetClassResponse>> getClasses() {

    List<TrainingClass> trainingClasses = classService.getTrainingClass();
    

    if (trainingClasses.isEmpty()) {
        return ResponseEntity.ok(new ArrayList <>());
    }

    List<GetClassResponse> responseList = trainingClasses.stream()
        .map(trainingClass -> GetClassResponse.builder()
            .idClass(trainingClass.getIdClass())
            .classDate(trainingClass.getClassDate())
            .classTime(trainingClass.getClassTime())
            .classCapasity(trainingClass.getClassCapasity())
            .build())
        .collect(Collectors.toList());


    return ResponseEntity.ok(responseList);
}
    
}
