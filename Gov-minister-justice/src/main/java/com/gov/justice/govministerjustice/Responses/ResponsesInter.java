package com.gov.justice.govministerjustice.Responses;

import com.gov.justice.govministerjustice.Responses.Data.DepartementResponse;
import com.gov.justice.govministerjustice.Responses.Data.LoginResponse;
import com.gov.justice.govministerjustice.Responses.Data.TransactionsResponse;
import org.springframework.http.ResponseEntity;

public interface ResponsesInter {

    ResponseEntity<LoginResponse> getLoginResponse(LoginResponse loginResponse);
    ResponseEntity<DepartementResponse> getDepartementResponse(DepartementResponse departementResponse);
    ResponseEntity<TransactionsResponse> getTransactionResponse(TransactionsResponse transactionsResponse);

}
