package com.gov.eco.govministereco.services.Admin;

import com.gov.eco.govministereco.Responses.LoginResponse;
import com.gov.eco.govministereco.Responses.Responses;
import com.gov.eco.govministereco.domains.EcoMinisterAdmin;
import com.gov.eco.govministereco.dto.AdminLoginDto;
import com.gov.eco.govministereco.repository.AdminRespository;
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
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImp implements AdminService {

    private final AdminRespository adminRespository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;
    private final Responses responses;
    private final AuthenticationManager authenticationManager;

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
}
