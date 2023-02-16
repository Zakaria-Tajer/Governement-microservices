package com.gov.justice.govministerjustice.controllers;

import com.gov.justice.govministerjustice.domains.Transactions;
import com.gov.justice.govministerjustice.dto.TransactionDto;
import com.gov.justice.govministerjustice.services.Departement.DepartementServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
@Slf4j
@RequiredArgsConstructor
public class DepartementController {

    private final DepartementServiceImp departementService;

    @GetMapping("/getAllTransactions")
    public ResponseEntity<?> getAllTransactions(){
        return ResponseEntity.ok().body(departementService.allTransactions());
    }
    @PostMapping("/addTransaction")
    public ResponseEntity<?> makeTransaction(@RequestBody TransactionDto transactions){
        return ResponseEntity.ok().body(
                departementService.makeTransactions(transactions)
        );
    }

//    Todo: just make a simple api between microservices and return response to eco-minister;
    @GetMapping("/getTransactionsId")
    public ResponseEntity<?> getTransactions(){
        return ResponseEntity.ok().body(departementService.getPosts());
    }
}
