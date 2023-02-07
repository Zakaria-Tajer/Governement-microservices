package com.gov.justice.govministerjustice.services.Admin;

import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<JusticeMinister> registerAdmin(JusticeMinister justiceMinister);
    ResponseEntity<?> loginAdmin(AdminLoginDto loginDto);
}