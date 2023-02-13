package com.gov.justice.govministerjustice.services.Admin;

import com.gov.justice.govministerjustice.domains.Departement;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import com.gov.justice.govministerjustice.dto.AddDepratementDto;
import com.gov.justice.govministerjustice.dto.AdminLoginDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    ResponseEntity<JusticeMinister> registerAdmin(JusticeMinister justiceMinister) throws IOException;
    ResponseEntity<?> loginAdmin(AdminLoginDto loginDto);
    Page<JusticeMinister> getAllAdmins();

    ResponseEntity<?> addAnEmployee(JusticeMinister employee);
    ResponseEntity<?> addDepartement(AddDepratementDto departement) throws IOException;
}
