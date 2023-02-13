package com.gov.justice.govministerjustice.Responses.Data;

import com.gov.justice.govministerjustice.domains.Departement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartementResponse {

    private Date timestamp;
    private int statusCode;
    private String message;

    private Departement departementData;

}
