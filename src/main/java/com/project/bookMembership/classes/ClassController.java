package com.project.bookMembership.classes;


import org.springframework.http.ResponseEntity;
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

    
}
