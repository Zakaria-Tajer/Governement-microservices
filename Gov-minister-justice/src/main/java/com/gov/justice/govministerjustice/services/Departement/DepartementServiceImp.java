package com.gov.justice.govministerjustice.services.Departement;

import com.gov.justice.govministerjustice.Responses.Data.TransactionDataResponse;
import com.gov.justice.govministerjustice.Responses.Data.TransactionsResponse;
import com.gov.justice.govministerjustice.Responses.Responses;
import com.gov.justice.govministerjustice.domains.Departement;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.domains.Transactions;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import com.gov.justice.govministerjustice.dto.DepartementDto;
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
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class DepartementServiceImp implements DepartementService {

    private final Responses responses;
    private final TransactionRepository transactionRepository;
    private final DepartementRepository departementRepository;
    private final AdminJusticeRepository adminJusticeRepository;

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
//        List<Departement> departementsList = departementRepository.getDepartementByDepartmentName(transactions.getDepartementTransactionsName());

//        return null;
//        List<DepartementDto> departement = departementsList
//                .stream()
//                .map(
//                        Departement -> new DepartementDto(
//                                Departement.getDepartmentName(),
//                                Departement.getDepartmentJob()
////                                Departement.getTransactions(),
////                                Departement.getPersonsList()
//                        )
//                ).collect(Collectors.toList());


        if (admin.isPresent()) {
//            if (departement.get(0).getPersons() == null) {
//                return ResponseEntity
//                        .badRequest()
//                        .body
//                                (
//                                        responses.getTransactionResponse(
//                                                new TransactionsResponse(
//                                                        Date.from(Instant.now()),
//                                                        400,
//                                                        "Cannot perform a transaction with no employees in department Assign some employees",
//                                                        null
//                                                )
//                                        )
//                                );
//            }



        }
        return ResponseEntity
                .badRequest()
                .body
                        (
                                responses.getTransactionResponse(
                                        new TransactionsResponse(
                                                Date.from(Instant.now()),
                                                400,
                                                "Cannot ",
                                                null
                                        )
                                )
                        );


    }

    @Override
    public ResponseEntity<?> AssginEmployeeToDepartement(AdminLoginDto employee) {
        return null;
    }
}
