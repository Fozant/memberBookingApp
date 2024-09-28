package com.project.bookMembership.classes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

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

    @PostMapping("/addDetail")
    public ResponseEntity<String> addDetail(@RequestBody ClassDetailRequest classDetailRequest) {
      
      
        classDetailService.addClassDetail(classDetailRequest);

        return ResponseEntity.ok("Class added successfully");
    }
    
    @GetMapping("/getClasses")
    public ResponseEntity<List<GetClassResponse>> getClasses() {

    List<TrainingClass> trainingClasses = classService.getTrainingClass();
    

    if (trainingClasses.isEmpty()) {
        throw new RuntimeException("No training classes found");
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
