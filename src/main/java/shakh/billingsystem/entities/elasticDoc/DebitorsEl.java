package shakh.billingsystem.entities.elasticDoc;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;

@Document(indexName = "debitors_index")
public class DebitorsEl {
    @Id
    private Long id;

    @Field
    private String name;
}
