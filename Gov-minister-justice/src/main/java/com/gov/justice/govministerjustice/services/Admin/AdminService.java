package com.gov.justice.govministerjustice.services.Admin;

import com.gov.clients.ecoMinister.MinisterDto;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.dto.AddDepratementDto;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    ResponseEntity<JusticeMinister> registerAdmin(JusticeMinister justiceMinister) throws IOException;
    ResponseEntity<?> loginAdmin(AdminLoginDto loginDto);
    List<MinisterDto> getAllAdmins();

    ResponseEntity<?> addAnEmployee(JusticeMinister employee);
    ResponseEntity<?> addDepartement(AddDepratementDto departement) throws IOException;
}
