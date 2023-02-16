package com.gov.justice.govministerjustice.services.Departement;

import com.gov.clients.ecoMinister.Post;
import com.gov.clients.ecoMinister.TransactionsDto;
import com.gov.justice.govministerjustice.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartementService {

    ResponseEntity<?> makeTransactions(TransactionDto transactions);

    List<Post> getPosts();

    List<TransactionsDto> allTransactions();


}
