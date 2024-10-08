package com.project.bookMembership.transaction;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookMembership.membership.MembershipRequest;
import com.project.bookMembership.membership.MembershipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
     private final TransactionService transactionService;

     @PostMapping("/get")
     public ResponseEntity<GetTransactionResponse> buymembership(@RequestParam Long id) {
         Optional<GetTransactionResponse> transactionResponse = transactionService.getById(id);
     
         if (transactionResponse.isPresent()) {
             // If the transaction response exists, return 200 OK with the response
             return ResponseEntity.ok(transactionResponse.get());
         } else {
             // If the transaction response is not found, return 404 Not Found
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
     
    
}
}