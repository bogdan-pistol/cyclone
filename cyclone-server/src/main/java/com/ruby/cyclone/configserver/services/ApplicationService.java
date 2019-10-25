package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.Application;
import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.repo.mongo.CountryRepository;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService {

    private NamespaceRepository namespaceRepository;
    private CountryRepository countryRepository;

    @Autowired
    public ApplicationService(NamespaceRepository namespaceRepository, CountryRepository countryRepository) {
        this.namespaceRepository = namespaceRepository;
        this.countryRepository = countryRepository;
    }

    public Set<Application> getApplications(String namespace) {
        return namespaceRepository.findById(namespace)
                .map(Namespace::getApplications)
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to get applications."));
    }


    public void archive(String namespace, String country) {
        Optional<Namespace> ns = this.namespaceRepository.findById(namespace);
        ns.map(nsDao -> {
            Set<Application> countries = nsDao.getApplications();
            countries.remove(country);
            nsDao.setApplications(countries);
            namespaceRepository.save(nsDao);
            return true;
        }).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to archive application."));
    }

    public Application addApp(String namespace, Application requestApplication) {

        Optional<Namespace> ns = this.namespaceRepository.findById(namespace);

        if(!ns.isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Namespace not found!");
        }

        // TODO: check unicity
        Application application = countryRepository.save(requestApplication);

        return ns.map(nsDao -> {
            Set<Application> countries = nsDao.getApplications();
            if (countries == null) {
                countries = new HashSet<>();
            }
            countries.add(application);
            nsDao.setApplications(countries);
            namespaceRepository.save(nsDao);
            return application;
        }).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to add application."));
    }
}
