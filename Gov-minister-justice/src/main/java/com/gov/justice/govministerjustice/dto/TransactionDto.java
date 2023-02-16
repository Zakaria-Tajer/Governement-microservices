package com.gov.justice.govministerjustice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionDto {
    private String transactionName;
    private String transactionDescription;
    private String AdminEmail;
    private String departementTransactionsName;
    private Date transactionDate;

}
