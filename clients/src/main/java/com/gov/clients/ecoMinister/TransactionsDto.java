package com.gov.clients.ecoMinister;


import java.util.Date;

public record TransactionsDto(
        String transactionId,
        String transactionName,
        String transactionDescription,
        Date transactionDate) {


}
