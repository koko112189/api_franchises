package com.api_franchises.api_franchises.entity.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "franchise")
public class Franchise {
     @Field("id")
    private String id;
    private String name;
    private String description;
    private List<Branch> branchs;
}
