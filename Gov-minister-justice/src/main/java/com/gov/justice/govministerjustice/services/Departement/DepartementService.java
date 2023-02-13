package com.gov.justice.govministerjustice.services.Departement;

import com.gov.justice.govministerjustice.domains.Transactions;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import com.gov.justice.govministerjustice.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface DepartementService {

    ResponseEntity<?> makeTransactions(TransactionDto transactions);
    ResponseEntity<?> AssginEmployeeToDepartement(AdminLoginDto employee);
}
