package com.gov.justice.govministerjustice.controllers;

import com.gov.justice.govministerjustice.domains.Transactions;
import com.gov.justice.govministerjustice.dto.TransactionDto;
import com.gov.justice.govministerjustice.services.Departement.DepartementServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
@Slf4j
@RequiredArgsConstructor
public class DepartementController {

//    private final DepartementServiceImp departementService;

    @RequestMapping("/addTransaction")
    public ResponseEntity<?> makeTransaction(@RequestBody TransactionDto transactions){
//        return ResponseEntity.ok().body(
//                departementService.makeTransactions(transactions)
//        );
        return null;
    }
}
