package com.gov.justice.govministerjustice.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "justdepartement")
@Data
@AllArgsConstructor
public class Departement {
    @Id
    @Field(type = FieldType.Keyword)
    private String departementId;
    @Field(type = FieldType.Text)
    private String departmentName;
    @Field(type = FieldType.Text)
    private String departmentJob;
    @Field(type = FieldType.Nested)
    private List<Transactions> transactions;
//    @Field(type = FieldType.Nested)
//    private List<JusticeMinister> personsList;

}
