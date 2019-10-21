package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.Country;
import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.repo.mongo.CountryRepository;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryService {

    private NamespaceRepository namespaceRepository;
    private CountryRepository countryRepository;

    @Autowired
    public CountryService(NamespaceRepository namespaceRepository, CountryRepository countryRepository) {
        this.namespaceRepository = namespaceRepository;
        this.countryRepository = countryRepository;
    }

    public Set<Country> getBusinesses(String namespace) {
        return namespaceRepository.findById(namespace)
                .map(Namespace::getCountries)
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to get countries."));
    }


    public void archive(String namespace, String country) {
        Optional<Namespace> ns = this.namespaceRepository.findById(namespace);
        ns.map(nsDao -> {
            Set<Country> countries = nsDao.getCountries();
            countries.remove(country);
            nsDao.setCountries(countries);
            namespaceRepository.save(nsDao);
            return true;
        }).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to archive country."));
    }

    public Country addCountry(String namespace, Country requestCountry) {

        Optional<Namespace> ns = this.namespaceRepository.findById(namespace);

        if(!ns.isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Namespace not found!");
        }

        // TODO: check unicity
        Country country = countryRepository.save(requestCountry);

        return ns.map(nsDao -> {
            Set<Country> countries = nsDao.getCountries();
            if (countries == null) {
                countries = new HashSet<>();
            }
            countries.add(country);
            nsDao.setCountries(countries);
            namespaceRepository.save(nsDao);
            return country;
        }).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to add country."));
    }
}
