package com.ruby.cyclone.configserver.models.business;

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
@CompoundIndex(def = "{'tenant':1, '_id':1}", unique = true, name = "tenant_name_idx")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Namespace {

    @Id
    private String name;

    @DBRef
    private Tenant tenant;

    private String description;

    @UniqueElements
    private Set<Application> applications = new HashSet<>();

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant modifiedAt;
}
