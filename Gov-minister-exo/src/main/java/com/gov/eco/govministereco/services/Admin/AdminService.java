package com.gov.eco.govministereco.services.Admin;

import com.gov.clients.ecoMinister.MinisterDto;
import com.gov.eco.govministereco.domains.EcoMinisterAdmin;
import com.gov.eco.govministereco.dto.AdminLoginDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    ResponseEntity<EcoMinisterAdmin> registerAdmin(EcoMinisterAdmin ecoMinisterAdmin);
    ResponseEntity<?> loginAdmin(AdminLoginDto loginDto);

    List<MinisterDto> getAdmins();
}
