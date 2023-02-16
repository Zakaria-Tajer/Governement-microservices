package com.gov.justice.govministerjustice.controllers;

import com.gov.clients.ecoMinister.MinisterDto;
import com.gov.justice.govministerjustice.Responses.Data.LoginResponse;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.dto.AddDepratementDto;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import com.gov.justice.govministerjustice.repository.AdminJusticeRepository;
import com.gov.justice.govministerjustice.services.Admin.AdminServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/justiceAdmin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImp adminServiceImp;
    @GetMapping("/auth/getAllAdmins")
    public List<MinisterDto> getALllAdmins() {
        return adminServiceImp.getAllAdmins();
    }
    @GetMapping("/hello")
    public  String hello() {
        return "hello";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<JusticeMinister> adminRegistration(@RequestBody JusticeMinister justiceMinister) {
        return ResponseEntity.ok().body(
                adminServiceImp.registerAdmin(justiceMinister).getBody()
        );
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody AdminLoginDto loginDto){
        log.info("Login Admin hit");
        return adminServiceImp.loginAdmin(loginDto);
    }

    @PostMapping("/services/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody JusticeMinister employee){
        log.info("Login employee hit");
        return ResponseEntity.ok().body(adminServiceImp.addAnEmployee(employee));
    }
    @PostMapping("/services/addDepartement")
    public ResponseEntity<?> addDepartement(@RequestBody AddDepratementDto departement) throws IOException{
        log.info("departement hit");
        return ResponseEntity.ok().body(adminServiceImp.addDepartement(departement));
    }

}
