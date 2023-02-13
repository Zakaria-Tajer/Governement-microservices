package com.gov.justice.govministerjustice.dto;

import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.domains.Transactions;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class DepartementDto {

    private String departmentName;
    private String departmentJob;
    private List<Transactions> transactions;
    private List<JusticeMinister> persons;
}
