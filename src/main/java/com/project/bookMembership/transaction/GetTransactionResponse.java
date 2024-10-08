package com.project.bookMembership.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionResponse {

    private Long idTransaction;
    private byte[] buktiTransfer;
}
