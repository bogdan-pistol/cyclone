package com.ruby.cyclone.configserver.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@Document(collection = "properties")
public class Property<T> {

    @Id
    private PropertyId id;

    private T value;

    private String description;

    @JsonIgnore
    @CreatedDate
    private Instant createdAt;

    @JsonIgnore
    @LastModifiedDate
    private Instant modifiedAt;

}


