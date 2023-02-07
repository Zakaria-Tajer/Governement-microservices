package com.gov.justice.govministerjustice.services.Admin;

import com.gov.justice.govministerjustice.Responses.LoginResponse;
import com.gov.justice.govministerjustice.Responses.Responses;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import com.gov.justice.govministerjustice.repository.AdminJusticeRepository;
import com.gov.justice.govministerjustice.security.ApplicationConfig;
import com.gov.justice.govministerjustice.security.JwtService;
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

    private final AdminJusticeRepository adminJusticeRepository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;
    private final Responses responses;
    private final AuthenticationManager authenticationManager;

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
