package com.gov.eco.govministereco.domains;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    private String transactionName;

    private String transactionDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MinisterTransactions", referencedColumnName = "ministerId")
    private EcoMinisterAdmin ministerAdmin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department", referencedColumnName = "departementId")
    private Departement departementTransactions;
    private Date transactionDate;
}
