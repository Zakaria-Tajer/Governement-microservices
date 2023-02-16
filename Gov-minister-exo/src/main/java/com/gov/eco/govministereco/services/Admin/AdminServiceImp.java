package com.gov.eco.govministereco.services.Admin;

import com.gov.clients.ecoMinister.DepartementDto;
import com.gov.clients.ecoMinister.JusticeMinisterClient;
import com.gov.clients.ecoMinister.MinisterDto;
import com.gov.clients.ecoMinister.TransactionsDto;
import com.gov.eco.govministereco.Responses.LoginResponse;
import com.gov.eco.govministereco.Responses.Responses;
import com.gov.eco.govministereco.domains.Departement;
import com.gov.eco.govministereco.domains.EcoMinisterAdmin;
import com.gov.eco.govministereco.domains.ForeignMinisters;
import com.gov.eco.govministereco.domains.Transactions;
import com.gov.eco.govministereco.dto.AdminLoginDto;
import com.gov.eco.govministereco.repository.AdminRespository;
import com.gov.eco.govministereco.repository.DepartementRepository;
import com.gov.eco.govministereco.repository.ForeignMinistersRepository;
import com.gov.eco.govministereco.repository.TransactionsRepository;
import com.gov.eco.govministereco.security.ApplicationConfig;
import com.gov.eco.govministereco.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImp implements AdminService {

    private final AdminRespository adminRespository;
    private final DepartementRepository departementRepository;
    private final TransactionsRepository transactionsRepository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;
    private final Responses responses;
    private final AuthenticationManager authenticationManager;
    private final JusticeMinisterClient justiceMinisterClient;
    private final ForeignMinistersRepository foreignMinisters;

    @Override
    public ResponseEntity<EcoMinisterAdmin> registerAdmin(EcoMinisterAdmin ecoMinisterAdmin) {
        ecoMinisterAdmin.setPassword(applicationConfig.passwordEncoder().encode(ecoMinisterAdmin.getPassword()));

        return ResponseEntity.ok().body(adminRespository.save(ecoMinisterAdmin));
    }

    @Override
    public ResponseEntity<LoginResponse> loginAdmin(AdminLoginDto loginDto) {
        if (loginDto.getEmail() == null) return
                responses
                        .getLoginResponse
                                (
                                        new LoginResponse(
                                                Date.from(Instant.now()),
                                                null,
                                                400,
                                                "Please enter a valid email"
                                        )
                                );


        Optional<EcoMinisterAdmin> admin = adminRespository.findAdminByEmail(loginDto.getEmail());

        if (admin.isEmpty()) return
                responses
                        .getLoginResponse
                                (
                                        new LoginResponse(
                                                Date.from(Instant.now()),
                                                null,
                                                400,
                                                "No user with this email"
                                        )
                                );


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

        log.info("admin " + admin.get().getPassword() + " " + loginDto.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
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
    public List<MinisterDto> getAdmins() {
        Optional<EcoMinisterAdmin> ministerAdmin = adminRespository.findById(1);
        List<MinisterDto> ministerDto = justiceMinisterClient.getAllAdmins();

        if (ministerAdmin.isPresent()) {
            List<DepartementDto> allDepartments = ministerDto
                    .stream()
                    .flatMap(admin -> admin.departements() == null ? null : admin.departements().stream())
                    .collect(Collectors.toList());

            log.info("Success. {}", allDepartments);

            List<TransactionsDto> trn = allDepartments
                    .stream()
                    .flatMap(transactions -> transactions.transactions().stream())
                    .toList();

            List<Transactions> transactions = trn.stream().map(transactionsDto ->

                    new Transactions(
                            0,
                            transactionsDto.transactionName(),
                            transactionsDto.transactionDescription(),
                            transactionsDto.transactionDate()
                    )

            ).collect(Collectors.toList());


            List<Transactions> transactionsList = transactionsRepository.saveAll(transactions);

            List<Departement> departments = allDepartments.stream()
                    .map(dep -> new Departement(
                            0,
                            dep.departmentName(),
                            dep.departmentJob(),
                            transactionsList
                    ))
                    .collect(Collectors.toList());

            List<ForeignMinisters> ministersForeign = ministerDto.stream().map(
                    foreigns -> new com.gov.eco.govministereco.domains.ForeignMinisters(
                            0,
                            foreigns.firstName(),
                            foreigns.lastName(),
                            foreigns.email(),
                            foreigns.role()
                    )
            ).collect(Collectors.toList());

            foreignMinisters.saveAll(ministersForeign);
            departementRepository.saveAll(departments);

            ministerAdmin.get().setDepartements(departments);

            adminRespository.save(ministerAdmin.get());


            log.info("Updated: {}", ministerAdmin.get());
        }


        return justiceMinisterClient.getAllAdmins();
    }
}
