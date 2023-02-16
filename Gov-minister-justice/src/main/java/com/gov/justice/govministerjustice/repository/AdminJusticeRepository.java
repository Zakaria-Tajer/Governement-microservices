package com.gov.justice.govministerjustice.repository;

import com.gov.justice.govministerjustice.domains.JusticeMinister;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AdminJusticeRepository extends ElasticsearchRepository<JusticeMinister, String> {
    Optional<JusticeMinister> findByEmail(String email);


}
