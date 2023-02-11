package com.gov.justice.govministerjustice.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departement {

    @Id
    private int departementId;
    private String departmentName;
    private String departmentJob;

    private List<Transactions> transactions;

    private JusticeMinister justiceMinister;

}
