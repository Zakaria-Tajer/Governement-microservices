package com.gov.eco.govministereco.controllers;

import com.gov.eco.govministereco.services.Departement.DepartementServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
@Slf4j
@RequiredArgsConstructor
public class DepartementController {

    private final DepartementServiceImp departementServiceImp;
    @GetMapping("/id")
    public ResponseEntity<?> getDepartementId(@RequestBody String id) {
        return ResponseEntity.ok(departementServiceImp.getTransactions());
    }
}
