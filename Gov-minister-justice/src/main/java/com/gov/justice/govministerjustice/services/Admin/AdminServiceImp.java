package com.gov.justice.govministerjustice.services.Admin;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.xcontent.XContentType;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.elasticsearch.xcontent.XContentFactory.jsonBuilder;


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

    private final ObjectMapper objectMapper;
    private final RestHighLevelClient client;

    @Override
    public Page<JusticeMinister> getAllAdmins() {
        return (Page<JusticeMinister>) adminJusticeRepository.findAll();
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

        log.info("admin lookup hit " + admin.get());

        if (admin.isPresent()) {
            if (admin.get().getRole().equals(Roles.EMPLOYEE)
                    && admin.get().getDepartements() != null) {

                log.info("emplooye lookup hit");

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
