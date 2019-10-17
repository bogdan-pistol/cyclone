package com.ruby.cyclone.configserver.models.business;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyId {

    private String key;
    private String namespace;
    private String country;
    private String file = "application.properties";
}


