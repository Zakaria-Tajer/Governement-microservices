package com.gov.justice.govministerjustice.repository;

import com.gov.justice.govministerjustice.domains.Departement;
import com.gov.justice.govministerjustice.domains.JusticeMinister;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AdminJusticeRepository extends ElasticsearchRepository<JusticeMinister, String> {
    Optional<JusticeMinister> findByEmail(String email);

    @Query("{" +
            "  \"script\" : {" +
            "    \"source\": \"(ctx._source['fieldName'] = params.fieldValue)\"," +
            "    \"params\" : {" +
            "      \"fieldValue\" : ?0" +
            "    }" +
            "  }" +
            "}")
    void updateField(String id, String fieldName, Object fieldValue);
}
