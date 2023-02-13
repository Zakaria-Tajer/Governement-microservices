package com.gov.justice.govministerjustice.Responses;


import com.gov.justice.govministerjustice.Responses.Data.DepartementResponse;
import com.gov.justice.govministerjustice.Responses.Data.LoginResponse;
import com.gov.justice.govministerjustice.Responses.Data.TransactionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Responses implements ResponsesInter{

    @Override
    public ResponseEntity<LoginResponse> getLoginResponse(LoginResponse loginResponse) {
        return ResponseEntity.ok().body(
                new LoginResponse(
                        loginResponse.getTimestamp(),
                        loginResponse.getToken(),
                        loginResponse.getStatusCode(),
                        loginResponse.getMessage()
                )
        );
    }

    @Override
    public ResponseEntity<DepartementResponse> getDepartementResponse(DepartementResponse departementResponse) {
        return ResponseEntity.ok().body(
                new DepartementResponse(
                        departementResponse.getTimestamp(),
                        departementResponse.getStatusCode(),
                        departementResponse.getMessage(),
                        departementResponse.getDepartementData()
                )
        );
    }

    @Override
    public ResponseEntity<TransactionsResponse> getTransactionResponse(TransactionsResponse transactionsResponse) {
        return ResponseEntity.ok().body(
                new TransactionsResponse(
                        transactionsResponse.getTimestamp(),
                        transactionsResponse.getStatusCode(),
                        transactionsResponse.getMessage(),
                        transactionsResponse.getTransactionData()
                )
        );
    }
}
