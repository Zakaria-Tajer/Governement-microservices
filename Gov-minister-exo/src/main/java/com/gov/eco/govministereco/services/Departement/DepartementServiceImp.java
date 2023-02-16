package com.gov.eco.govministereco.services.Departement;

import com.gov.clients.ecoMinister.JusticeMinisterClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class DepartementServiceImp implements DepartementService {

    private final JusticeMinisterClient justiceMinisterClient;

    @Override
    public ResponseEntity<?> getTransactions() {

        return null;
    }
}
