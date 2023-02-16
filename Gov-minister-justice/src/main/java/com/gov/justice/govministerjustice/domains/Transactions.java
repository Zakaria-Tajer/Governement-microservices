package com.gov.justice.govministerjustice.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Document(indexName = "transactions")
@Data
@AllArgsConstructor
public class Transactions {

    @Id
    @Field(type = FieldType.Keyword)
    private String transactionId;
    @Field(type = FieldType.Text)
    private String transactionName;
    @Field(type = FieldType.Text)
    private String transactionDescription;
    @Field(type = FieldType.Date)
    private Date transactionDate;
}
