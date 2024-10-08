package com.project.bookMembership.transaction;

import java.util.Optional;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.IOException;
import utils.ImageUtils;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    @Override
    public Optional<GetTransactionResponse> getById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
    
        return transaction.map(tx -> {
            GetTransactionResponse response = new GetTransactionResponse();
            response.setIdTransaction(tx.getIdTransaction());
    
            try {
                // Decompress the buktiTransfer image data
                byte[] decompressedImage = ImageUtils.decompressImage(tx.getBuktiTransfer());
                response.setBuktiTransfer(decompressedImage);
                return response; // Return the response object
            } catch (DataFormatException | java.io.IOException exception) {
                // Handle the exceptions and wrap them in RuntimeException
                throw new RuntimeException("Error decompressing image for Transaction ID: " + tx.getIdTransaction(), exception);
            } 
        });
    }
    }
