package com.ruby.cyclone.configserver.repo.mongo;

import com.ruby.cyclone.configserver.models.business.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CountryRepository extends MongoRepository<Application, String> {

    public Optional<Application> findByCountry(String country);

}
