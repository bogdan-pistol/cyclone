package com.ruby.cyclone.configserver.models.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@Document(collection = "properties")
public class Property<T> {

    @Id
    private PropertyId id;

    private T value;

    private String description;

}


