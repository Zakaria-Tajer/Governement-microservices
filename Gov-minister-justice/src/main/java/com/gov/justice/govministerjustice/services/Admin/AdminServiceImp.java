package com.gov.justice.govministerjustice.services.Admin;

import com.gov.clients.ecoMinister.DepartementDto;
import com.gov.clients.ecoMinister.MinisterDto;
import com.gov.clients.ecoMinister.TransactionsDto;
import com.gov.justice.govministerjustice.Responses.Data.DepartementResponse;
import com.gov.justice.govministerjustice.Responses.Data.LoginResponse;
import com.gov.justice.govministerjustice.Responses.Responses;
import com.gov.justice.govministerjustice.domains.Departement;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.dto.AddDepratementDto;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import com.gov.justice.govministerjustice.enums.Roles;
import com.gov.justice.govministerjustice.repository.AdminJusticeRepository;

import com.gov.justice.govministerjustice.repository.DepartementRepository;
import com.gov.justice.govministerjustice.security.ApplicationConfig;
import com.gov.justice.govministerjustice.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImp implements AdminService {

    private final AdminJusticeRepository adminJusticeRepository;
    private final DepartementRepository departementRepository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;
    private final Responses responses;
    private final AuthenticationManager authenticationManager;


    @Override
    public List<MinisterDto> getAllAdmins() {
        List<JusticeMinister> d =  adminJusticeRepository.findAll(PageRequest.of(0, 10)).toList();

//        return null;
        return  d.stream().map(
                minsister ->
                        new MinisterDto(
                                minsister.getId(),
                                minsister.getFirstName(),
                                minsister.getLastName(),
                                minsister.getEmail(),
                                minsister.getPassword(),
                                minsister.getRole().name(),
                                minsister.getDepartements() != null ?
                                        minsister.getDepartements().stream().map(
                                                departement -> new DepartementDto(
                                                        departement.getDepartementId(),
                                                        departement.getDepartmentName(),
                                                        departement.getDepartmentJob(),
                                                        departement.getTransactions().stream().map(
                                                        transactions -> new TransactionsDto(
                                                                transactions.getTransactionId(),
                                                                transactions.getTransactionName(),
                                                                transactions.getTransactionDescription(),
                                                                transactions.getTransactionDate()
                                                        )
                                                ).collect(Collectors.toList())
                                                )
                                        ).collect(Collectors.toList())
                                : null

                        )
        ).collect(Collectors.toList());
    }


    @Override
    public ResponseEntity<JusticeMinister> registerAdmin(JusticeMinister ecoMinisterAdmin) {

        ecoMinisterAdmin.setPassword(applicationConfig.passwordEncoder().encode(ecoMinisterAdmin.getPassword()));
        return ResponseEntity.ok().body(adminJusticeRepository.save(ecoMinisterAdmin));
    }

    @Override
    public ResponseEntity<LoginResponse> loginAdmin(AdminLoginDto loginDto) {
        if (loginDto == null) {
            return
                    responses
                            .getLoginResponse
                                    (
                                            new LoginResponse(
                                                    Date.from(Instant.now()),
                                                    null,
                                                    400,
                                                    "All Fields are required"
                                            )
                                    );
        }


        Optional<JusticeMinister> admin = adminJusticeRepository.findByEmail(loginDto.getEmail());

//        log.info("admin email: " + admin.get());

        if (admin.isEmpty()) {
            return responses
                    .getLoginResponse
                            (
                                    new LoginResponse(
                                            Date.from(Instant.now()),
                                            null,
                                            400,
                                            "No user with this email"
                                    )
                            );
        }


        if (!applicationConfig.passwordEncoder().matches(loginDto.getPassword(), admin.get().getPassword())) {
            return
                    responses
                            .getLoginResponse
                                    (
                                            new LoginResponse(
                                                    Date.from(Instant.now()),
                                                    null,
                                                    400,
                                                    "Passwords do not match"
                                            )
                                    );

        }


        String jwtToken = jwtService.generateToken(admin.get());


        return
                responses
                        .getLoginResponse
                                (
                                        new LoginResponse(
                                                Date.from(Instant.now()),
                                                jwtToken,
                                                201,
                                                "success"
                                        )
                                );

    }


    @Override
    public ResponseEntity<?> addAnEmployee(JusticeMinister employee) {
        if (employee == null) {
            return
                    responses
                            .getLoginResponse
                                    (
                                            new LoginResponse(
                                                    Date.from(Instant.now()),
                                                    null,
                                                    400,
                                                    "All Fields are required"
                                            )
                                    );
        }
        employee.setPassword(applicationConfig.passwordEncoder().encode(employee.getPassword()));
        return ResponseEntity.ok().body(adminJusticeRepository.save(employee));

    }

    @Override
    public ResponseEntity<?> addDepartement(AddDepratementDto departement) throws IOException {
        if (departement == null) {
            return
                    responses
                            .getDepartementResponse
                                    (
                                            new DepartementResponse(
                                                    Date.from(Instant.now()),
                                                    400,
                                                    "All Fields are required",
                                                    null
                                            )
                                    );
        }
        Optional<JusticeMinister> admin = adminJusticeRepository.findByEmail(departement.getPersonsEmail());


        if (admin.isPresent()) {
            if (admin.get().getRole().equals(Roles.EMPLOYEE)
                    && admin.get().getDepartements() != null) {

                return
                        responses
                                .getDepartementResponse
                                        (
                                                new DepartementResponse(
                                                        Date.from(Instant.now()),
                                                        400,
                                                        "Employee is already assigned to a department",
                                                        null
                                                )
                                        );
            }

            Departement departements = new Departement(
                    "",
                    departement.getDepartmentName(),
                    departement.getDepartmentJob(),
                    departement.getTransactions()
            );

            Departement departementData = departementRepository.save(
                    departements
            );


            admin.get().setDepartements(List.of(departementData));
            adminJusticeRepository.save(admin.get());



            return ResponseEntity.ok().body(
                    responses
                            .getDepartementResponse
                                    (
                                            new DepartementResponse(
                                                    Date.from(Instant.now()),
                                                    400,
                                                    "success",
                                                    departements
                                            )
                                    )
            );
        }

        return ResponseEntity.ok().body(
                responses
                        .getDepartementResponse
                                (
                                        new DepartementResponse(
                                                Date.from(Instant.now()),
                                                400,
                                                "No employee with email :" + " " + departement.getPersonsEmail(),
                                                null
                                        )
                                )
        );
    }



}
