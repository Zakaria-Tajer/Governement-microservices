package com.gov.justice.govministerjustice.Responses.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsResponse {

    private Date timestamp;
    private int statusCode;
    private String message;
    private TransactionDataResponse transactionData;
}
