package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.api.request.AddNewPropertyRequest;
import com.ruby.cyclone.configserver.models.api.request.PropertyLocation;
import com.ruby.cyclone.configserver.models.api.request.UpdatePropertyRequest;
import com.ruby.cyclone.configserver.models.business.Property;
import com.ruby.cyclone.configserver.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/properties")
public class PropertiesController {


    private PropertiesService propertiesService;

    @Autowired
    public PropertiesController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<PropertyLocation, List<Property>> searchProperties(@RequestParam(defaultValue = "") String tenant,
                                                                  @RequestParam(defaultValue = "") String namespace,
                                                                  @RequestParam(defaultValue = "") String application,
                                                                  @RequestParam(defaultValue = "") String key) {
        return propertiesService.searchProperties(tenant, namespace, application, key);
    }

    @PostMapping
    public void addNewProperty(@RequestBody AddNewPropertyRequest propertyRequest) {
        propertiesService.addProperty(propertyRequest);
    }

    @PutMapping
    public void changeProperty(@RequestBody UpdatePropertyRequest propertyRequest) {
        propertiesService.updateProperty(propertyRequest);
    }

    @DeleteMapping("/archive/{propertyKey}")
    public void deleteProperty(@PathVariable String propertyKey) {

    }
}
