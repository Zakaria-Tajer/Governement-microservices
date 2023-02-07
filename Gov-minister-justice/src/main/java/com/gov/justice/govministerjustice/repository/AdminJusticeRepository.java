package com.gov.justice.govministerjustice.repository;

import com.gov.justice.govministerjustice.domains.JusticeMinister;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminJusticeRepository extends MongoRepository<JusticeMinister, String> {
    Optional<JusticeMinister> findByEmail(String email);
}
