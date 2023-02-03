package com.gov.eco.govministereco.domains;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class EcoMinisterAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ministerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "ecoMinisterAdmin", fetch = FetchType.LAZY)
    private List<Departement> departements;

}
