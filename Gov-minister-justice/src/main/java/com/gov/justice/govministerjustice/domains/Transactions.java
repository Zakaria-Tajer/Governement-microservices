package com.gov.justice.govministerjustice.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    private int transactionId;

    private String transactionName;

    private String transactionDescription;

    private JusticeMinister justiceMinister;
    private Departement departementTransactions;
    private Date transactionDate;
}
