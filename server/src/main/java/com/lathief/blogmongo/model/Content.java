package com.lathief.blogmongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "blogmongo")
@NoArgsConstructor
public class Content {
    @Id
    private String id;
    private String title;
    private String description;
    private String body;
    private String author;
    private String date_posted;
}
