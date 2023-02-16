package com.gov.justice.govministerjustice.dto;

import java.util.Date;


public record TransactionsRecord(
        String transactionId,
        String transactionName,
        String transactionDescription,
        Date transactionDate
){



}