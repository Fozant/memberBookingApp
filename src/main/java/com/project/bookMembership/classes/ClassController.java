package com.project.bookMembership.classes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookMembership.config.JwtService;
import com.project.bookMembership.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final JwtService jwtService;
    private final UserService userService;
    private final ClassDetailService classDetailService;

    @PostMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody ClassRequest classRequest) {
       
        classService.save(classRequest); 

        return ResponseEntity.ok("Class added successfully");
    }

    @PostMapping("/addDetail")
    public ResponseEntity<String> addDetail(@RequestBody ClassDetailRequest classDetailRequest) {
      
        String username = jwtService.extractUsername(classDetailRequest.getToken());

        
        Long userId = userService.getUserIdByUsername(username);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        
        classDetailRequest.setIdUser(userId);

        
        classDetailService.addClassDetail(classDetailRequest);

        return ResponseEntity.ok("Class added successfully");
    }

    
}
