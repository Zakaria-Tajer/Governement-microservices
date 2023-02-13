package com.gov.justice.govministerjustice.repository;

import com.gov.justice.govministerjustice.domains.Transactions;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ElasticsearchRepository<Transactions, String> {
}
