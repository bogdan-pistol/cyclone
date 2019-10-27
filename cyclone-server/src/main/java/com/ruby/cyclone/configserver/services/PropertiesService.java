package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.api.request.AddNewPropertyRequest;
import com.ruby.cyclone.configserver.models.api.request.PropertyLocation;
import com.ruby.cyclone.configserver.models.api.request.UpdatePropertyRequest;
import com.ruby.cyclone.configserver.models.business.Application;
import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.models.business.Property;
import com.ruby.cyclone.configserver.models.business.PropertyId;
import com.ruby.cyclone.configserver.repo.mongo.ApplicationRepo;
import com.ruby.cyclone.configserver.repo.mongo.PropertiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropertiesService {

    private PropertiesRepo propertiesRepo;
    private ApplicationRepo applicationRepo;

    @Autowired
    public PropertiesService(PropertiesRepo propertiesRepo, ApplicationRepo applicationRepo) {
        this.propertiesRepo = propertiesRepo;
        this.applicationRepo = applicationRepo;
    }


    public Map<PropertyLocation, List<Property>> searchProperties(String tenant, String namespace, String business, String keyWord) {
        List<Property> properties = propertiesRepo.searchByKeyAndLocationRegexes(tenant, namespace, business, "", keyWord);
        return groupProperties(properties);
    }

    private Map<PropertyLocation, List<Property>> groupProperties(List<Property> properties) {
        if (properties == null || properties.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<PropertyLocation, List<Property>> propertiesMap = new HashMap<>();
        for (Property property : properties) {
            String ns = property.getId().getNamespace();
            String country = property.getId().getApplication();
            PropertyLocation location = PropertyLocation.builder().namespace(ns).country(country).build();
            if (propertiesMap.containsKey(location)) {
                List<Property> propertyList = propertiesMap.get(location);
                propertyList.add(property);
            } else {
                propertiesMap.put(location, new ArrayList<>());
                propertiesMap.get(location).add(property);
            }
        }
        return propertiesMap;
    }

    public void addProperty(AddNewPropertyRequest propertyRequest) {

        List<Application> applications = this.applicationRepo.findAll();
        if (applications == null || applications.isEmpty()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "No applications defined.");
        }

        List<Property> properties = buildProperties(propertyRequest, applications);

        propertiesRepo.saveAll(properties);
    }

    private List<Property> buildProperties(@NotNull AddNewPropertyRequest propertyRequest, @NotNull List<Application> apps) {
        return apps.stream()
                .map(Application::getId)
                .map(id -> PropertyId.builder()
                        .tenant(id.getNamespace().getTenant())
                        .namespace(id.getNamespace().getNamespace())
                        .application(id.getApplication())
                        .file(propertyRequest.getFile())
                        .key(propertyRequest.getKey())
                        .build()
                ).map(pId -> {
                    Property property = new Property();
                    property.setId(pId);
                    property.setDescription(propertyRequest.getDescription());
                    property.setValue(propertyRequest.getDefaultValue());
                    return property;
                })
                .collect(Collectors.toList());
    }

    public void updateProperty(UpdatePropertyRequest propertyRequest) {
        String propertyKey = propertyRequest.getKey();
        Map<PropertyLocation, Object> valuesByLocation = propertyRequest.getValuesByLocation();
        valuesByLocation.keySet().forEach(location -> {
            PropertyId propertyId = PropertyId.builder().application(location.getCountry())
                    .namespace(location.getNamespace())
                    .key(propertyKey)
                    .build();

            Optional<Property> property = propertiesRepo.findById(propertyId);
            property.map(p -> {
                p.setValue(valuesByLocation.get(location));
                return propertiesRepo.save(p);
            }).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "This property does not exists."));

        });
    }

}
