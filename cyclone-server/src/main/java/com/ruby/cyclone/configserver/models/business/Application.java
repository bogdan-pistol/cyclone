package com.ruby.cyclone.configserver.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "applications")
@Data
@Builder
@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Application {

    @Id
    private AppId id;

    private String description;

    @JsonIgnore
    @Field
    @UniqueElements
    private Set<PropsFile> files = new HashSet<>();

    @JsonIgnore
    @CreatedDate
    private Instant createdAt;

    @JsonIgnore
    @LastModifiedDate
    private Instant modifiedAt;

}
