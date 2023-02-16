package com.gov.clients.ecoMinister;

import java.util.List;



public record DepartementDto(
        String departementId,
        String departmentName,
        String departmentJob,
        List<TransactionsDto> transactions
) {

}
