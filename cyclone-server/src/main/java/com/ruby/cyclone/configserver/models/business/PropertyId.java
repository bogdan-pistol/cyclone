package com.ruby.cyclone.configserver.models.business;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyId {

    private String namespace;
    private String business;
    private String key;
    private String file = "application.properties";
}

