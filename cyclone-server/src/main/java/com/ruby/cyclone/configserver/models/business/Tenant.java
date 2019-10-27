package com.ruby.cyclone.configserver.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {

    @Id
    private String id;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    private Instant createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @LastModifiedDate
    private Instant modifiedAt;
}
