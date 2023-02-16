package com.gov.clients.ecoMinister;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        value = "JUSTICEMINISTER",
//        url = "http://localhost:8081/api/v1/justiceMinister"
        path = "api/v1/justiceAdmin"
)
public interface JusticeMinisterClient {
    @GetMapping("/posts")
    List<Post> getTransactions();

    @GetMapping("auth/getAllAdmins")
    List<MinisterDto> getAllAdmins();

    @GetMapping("/hello")
    String getSTring();


}
