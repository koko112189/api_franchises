package com.api_franchises.api_franchises.entity.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.api_franchises.api_franchises.entity.model.Branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "branch")
public class Branch {
      @Id
    private String id;
    private String name;
    private String description;
    private List<Product> products;
}
