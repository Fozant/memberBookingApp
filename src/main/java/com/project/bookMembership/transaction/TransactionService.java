package com.project.bookMembership.transaction;

import java.util.Optional;

public interface TransactionService {
    Transaction save(Transaction transaction);
    Optional<GetTransactionResponse> getById(Long id);
}
