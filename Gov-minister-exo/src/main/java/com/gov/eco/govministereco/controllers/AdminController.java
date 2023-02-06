package com.gov.eco.govministereco.controllers;


import com.gov.eco.govministereco.Responses.LoginResponse;
import com.gov.eco.govministereco.domains.EcoMinisterAdmin;
import com.gov.eco.govministereco.dto.AdminLoginDto;
import com.gov.eco.govministereco.services.Admin.AdminServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {


    private final AdminServiceImp adminServiceImp;

    @PostMapping("/auth/register")
    public ResponseEntity<EcoMinisterAdmin> adminRegistration(@RequestBody EcoMinisterAdmin adminLogin){
        return ResponseEntity.ok().body(
                adminServiceImp.registerAdmin(adminLogin).getBody()
        );
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody AdminLoginDto loginDto){
        return adminServiceImp.loginAdmin(loginDto);
    }

}
