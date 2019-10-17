package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/namespaces/{namespace}/countries")
public class CountriesController {

    private CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public List<String> getBusinesses(@PathVariable String namespace) {
        return countryService.getBusinesses(namespace);
    }

    @PostMapping()
    public String addBusiness(@PathVariable String namespace, @RequestBody String country) {
        return UUID.randomUUID().toString();
    }

    @DeleteMapping("/{country}/archive")
    public void archive(@PathVariable String namespace, @PathVariable String country) {
        this.countryService.archive(country);
    }

}
