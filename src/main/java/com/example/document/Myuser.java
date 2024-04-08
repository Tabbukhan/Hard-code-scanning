package com.example.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="MyUser")
@Data
@AllArgsConstructor
public class Myuser {

    private String id;
    private String name;
    private String password = "sbokade123";

}
