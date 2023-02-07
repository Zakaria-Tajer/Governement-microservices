package com.gov.justice.govministerjustice.controllers;

import com.gov.justice.govministerjustice.Responses.LoginResponse;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import com.gov.justice.govministerjustice.services.Admin.AdminServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/justiceAdmin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {


    private final AdminServiceImp adminServiceImp;

    @PostMapping("/auth/register")
    public ResponseEntity<JusticeMinister> adminRegistration(@RequestBody JusticeMinister justiceMinister){
        return ResponseEntity.ok().body(
                adminServiceImp.registerAdmin(justiceMinister).getBody()
        );
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody AdminLoginDto loginDto){
        return adminServiceImp.loginAdmin(loginDto);
    }

}
