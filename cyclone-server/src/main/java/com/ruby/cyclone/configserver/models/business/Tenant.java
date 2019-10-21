package com.ruby.cyclone.configserver.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "tenants")
@Data
@Builder
@EqualsAndHashCode(exclude = {"namespaces"})
@ToString(exclude = {"namespaces"})
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {

    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String name;

    private String description;

    @JsonIgnore
    @DBRef
    private List<Namespace> namespaces = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant modifiedAt;
}
