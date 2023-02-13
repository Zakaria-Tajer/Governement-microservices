package com.gov.justice.govministerjustice.Responses.Data;

import com.gov.justice.govministerjustice.domains.Transactions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDataResponse {
   private Transactions transactionsData;
}
