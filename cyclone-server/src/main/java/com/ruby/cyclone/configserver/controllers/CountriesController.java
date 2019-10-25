package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Country;
import com.ruby.cyclone.configserver.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/namespaces/{namespace}/countries")
public class CountriesController {

    private CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public Set<Country> getCountries(@PathVariable String namespace) {
        return countryService.getBusinesses(namespace);
    }

    @PostMapping()
    public Country addCountry(@PathVariable String namespace, @RequestBody Country country) {
        return countryService.addCountry(namespace, country);
    }

    @DeleteMapping("/{country}/archive")
    public void archive(@PathVariable String namespace, @PathVariable String country) {
        this.countryService.archive(namespace, country);
    }

}
