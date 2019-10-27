package com.ruby.cyclone.configserver.models.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NamespaceId {

    private String namespace;

    @JsonIgnore
    private String tenant;
}
