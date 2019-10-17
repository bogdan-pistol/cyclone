package com.ruby.cyclone.configserver.models.api.request;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AddNewPropertyRequest<T> {
    private String key;
    private T defaultValue;
    private String file;
    private String description;
}
