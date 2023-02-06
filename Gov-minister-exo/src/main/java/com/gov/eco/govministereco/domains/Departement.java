package com.gov.eco.govministereco.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departement{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int departementId;
    private String departmentName;
    private String departmentJob;

    @OneToMany(mappedBy = "departementTransactions", cascade = CascadeType.ALL)
//    @JoinColumn(name = "departmentTransaction", referencedColumnName = "departementTransactions")
    private List<Transactions> transactions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ecoMinsiterId", referencedColumnName = "ministerId")
//    @JsonIgnore
    private EcoMinisterAdmin ecoMinisterAdmin;

}
