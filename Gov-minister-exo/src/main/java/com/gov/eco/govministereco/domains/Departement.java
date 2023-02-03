package com.gov.eco.govministereco.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Departement {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer departementId;
    private String departmentName;
    private String departmentJob;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentTransaction", referencedColumnName = "departementTransactions")
    private List<Transactions> transactions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ecoMinsiterId", referencedColumnName = "departements")
    @JsonIgnore
    private EcoMinisterAdmin ecoMinisterAdmin;

}
