package com.gov.justice.govministerjustice.services.Departement;

import com.gov.clients.ecoMinister.JusticeMinisterClient;
import com.gov.clients.ecoMinister.Post;
import com.gov.clients.ecoMinister.TransactionsDto;
import com.gov.justice.govministerjustice.Responses.Data.TransactionsResponse;
import com.gov.justice.govministerjustice.Responses.Responses;
import com.gov.justice.govministerjustice.domains.Departement;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.domains.Transactions;
import com.gov.justice.govministerjustice.dto.TransactionDto;
import com.gov.justice.govministerjustice.repository.AdminJusticeRepository;
import com.gov.justice.govministerjustice.repository.DepartementRepository;
import com.gov.justice.govministerjustice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class DepartementServiceImp implements DepartementService {

    private final Responses responses;
    private final TransactionRepository transactionRepository;
    private final DepartementRepository departementRepository;
    private final AdminJusticeRepository adminJusticeRepository;

    private final JusticeMinisterClient justiceMinisterClient;

    //    TODO: create a method to add transactions and then save it and then save it in the microservice eco

    @Override
    public ResponseEntity<?> makeTransactions(TransactionDto transactions) {
        if (transactions == null) {
            return ResponseEntity
                    .badRequest()
                    .body
                            (
                                    responses.getTransactionResponse(
                                            new TransactionsResponse(
                                                    Date.from(Instant.now()),
                                                    400,
                                                    "All Fields are required",
                                                    null
                                            )
                                    )
                            );
        }

        Optional<JusticeMinister> admin = adminJusticeRepository.findByEmail(transactions.getAdminEmail());

        if (admin.isPresent()) {
            Departement departments = departementRepository.getDepartementByDepartmentName(transactions.getDepartementTransactionsName());
            if (departments != null) {

                Transactions successfulTransaction = transactionRepository.save(new Transactions(
                        "",
                        transactions.getTransactionName(),
                        transactions.getTransactionDescription(),
                        transactions.getTransactionDate() == null ?
                                Date.from(Instant.now()) : transactions.getTransactionDate()
                ));

                departments.setTransactions(List.of(successfulTransaction));
                admin.get().setDepartements(List.of(departments));

                adminJusticeRepository.save(admin.get());
                departementRepository.save(departments);


                return ResponseEntity
                        .ok()
                        .body
                                (
                                        responses.getTransactionResponse(
                                                new TransactionsResponse(
                                                        Date.from(Instant.now()),
                                                        201,
                                                        "successful Transaction",
                                                        successfulTransaction
                                                )
                                        )
                                );


            }
            return ResponseEntity
                    .badRequest()
                    .body
                            (
                                    responses.getTransactionResponse(
                                            new TransactionsResponse(
                                                    Date.from(Instant.now()),
                                                    400,
                                                    "No Department with this name: " + transactions.getDepartementTransactionsName(),
                                                    null
                                            )
                                    )
                            );
        }


        return ResponseEntity
                .badRequest()
                .body
                        (
                                responses.getTransactionResponse(
                                        new TransactionsResponse(
                                                Date.from(Instant.now()),
                                                400,
                                                "No User with this email: " + transactions.getAdminEmail(),
                                                null
                                        )
                                )
                        );


    }

    @Override
    public List<Post> getPosts() {

        return justiceMinisterClient.getTransactions();
    }

    @Override
    public List<TransactionsDto> allTransactions() {
        List<Transactions> transactions = (List<Transactions>) transactionRepository.findAll();


        return null;
//        return  transactionRepository.findAll();
    }


}
