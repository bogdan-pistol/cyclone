package com.ruby.cyclone.configserver.models.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "countries")
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Country {

    @Id
    private String id;


    private String country;

    private String description;

    @Field
    @UniqueElements
    private Set<FileName> files = new HashSet<>();

}
