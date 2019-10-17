package com.ruby.cyclone.configserver.models.api.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class PropertyLocation {
    private String namespace;
    private String country;
}
