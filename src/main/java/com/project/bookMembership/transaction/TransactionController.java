package com.project.bookMembership.transaction;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
     private final TransactionService transactionService;

     @PostMapping("/get")
     public ResponseEntity<GetTransactionResponse> getTransaction(@RequestParam Long id) {
         Optional<GetTransactionResponse> transactionResponse = transactionService.getById(id);
     
         if (transactionResponse.isPresent()) {
             
             return ResponseEntity.ok(transactionResponse.get());
         } else {
             
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
        // return ResponseEntity.of(transactionResponse);
}
}