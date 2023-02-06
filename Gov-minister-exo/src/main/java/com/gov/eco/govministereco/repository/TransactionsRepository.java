package com.gov.eco.govministereco.repository;

import com.gov.eco.govministereco.domains.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
}
