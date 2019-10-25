package com.ruby.cyclone.configserver.repo.mongo;

import com.ruby.cyclone.configserver.models.business.Property;
import com.ruby.cyclone.configserver.models.business.PropertyId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PropertiesRepository extends MongoRepository<Property, PropertyId> {


    @Query("{" +
            "'_id.tenant': {'$regex' : ?0}, " +
            "'_id.namespace': {'$regex' : ?1}, " +
            "'_id.application':{'$regex' : ?2}, " +
            "'_id.file':{'$regex' : ?3}, " +
            "'_id.key':{'$regex' : ?4}}")
    List<Property> searchByKeyAndLocationRegexes(String tenant, String namespace, String country, String file, String key);


    @Query("{'_id.tenant': ?0, '_id.namespace': ?1, '_id.application': ?2, '_id.file': ?3 }")
    List<Property> getPropertiesFromFile(String tenant, String namespace, String country, String file);


}
