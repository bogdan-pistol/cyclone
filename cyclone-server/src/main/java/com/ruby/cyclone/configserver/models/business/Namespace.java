package com.ruby.cyclone.configserver.models.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "namespaces")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Namespace {

    @Id
    private NamespaceId id;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Instant createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Instant modifiedAt;
}
