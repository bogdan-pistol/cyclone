package com.ruby.cyclone.configserver.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PropsFile {

    String name;

    @CreatedDate
    @JsonIgnore
    private Instant createdAt;

    @JsonIgnore
    @LastModifiedDate
    private Instant modifiedAt;

    public PropsFile(String name) {
        this.name = name;
    }
}
