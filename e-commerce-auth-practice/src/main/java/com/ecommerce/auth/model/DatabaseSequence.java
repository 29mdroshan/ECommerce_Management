package com.ecommerce.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;



import lombok.Data;

@Document(collection = "database_sequences")
@Component
@Data
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;
}