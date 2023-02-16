package com.gov.clients.ecoMinister;


import java.util.List;

public record MinisterDto(
        String id,
        String firstName,
        String lastName,
        String email,
        String password,
        String role,
        List<DepartementDto> departements
) {


}
