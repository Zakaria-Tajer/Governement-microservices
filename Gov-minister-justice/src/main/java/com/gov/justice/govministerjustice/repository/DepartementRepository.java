package com.gov.justice.govministerjustice.repository;

import com.gov.justice.govministerjustice.domains.Departement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends ElasticsearchRepository<Departement, String> {

    Departement getDepartementByDepartmentName(String name);

}
