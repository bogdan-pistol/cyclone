package com.ruby.cyclone.configserver.models.business;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@EqualsAndHashCode
@Builder
public class PropertyId {

    private String tenant;
    @Indexed
    private String namespace;
    @Indexed
    private String application;
    @Indexed
    private String file;
    @Indexed
    private String key;

}
