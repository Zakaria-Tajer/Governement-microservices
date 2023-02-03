package com.gov.eco.govministereco.domains;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transactions {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    private String transactionName;

    private String transactionDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MinisterTransactions", referencedColumnName = "ministerId")
    private EcoMinisterAdmin ministerAdmin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department", referencedColumnName = "transactions")
    private Departement departementTransactions;
    private Date transactionDate;
}
