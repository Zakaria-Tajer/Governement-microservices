package com.gov.eco.govministereco.repository;

import com.gov.eco.govministereco.domains.EcoMinisterAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRespository extends JpaRepository<EcoMinisterAdmin, Integer> {

    Optional<EcoMinisterAdmin> findAdminByEmail(String email);
}
