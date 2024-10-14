package com.project.bookMembership.visitPackage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/visitpackage")
@RequiredArgsConstructor
public class VisitPackageController {


    private final VisitPackageService visitPackageService;

    @PostMapping("/buy")
    public ResponseEntity<String> addClass(@RequestBody VisitPackageRequest visitPackageRequest) {
       
        visitPackageService.save(visitPackageRequest); 

        return ResponseEntity.ok("Buy visit package successfully");
    }


}
