package com.ruby.cyclone.configserver.models.business;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class AppId {

    private String application;
    @JsonIgnore
    private NamespaceId namespace;

}
