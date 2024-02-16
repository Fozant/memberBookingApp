package com.project.bookMembership.membership;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;


    @PostMapping("/buy")
    public ResponseEntity<String> addClass(@RequestBody MembershipRequest membershipRequest) {
       
        membershipService.save(membershipRequest); 

        return ResponseEntity.ok("Buy membership successfully");
    }

    


}
