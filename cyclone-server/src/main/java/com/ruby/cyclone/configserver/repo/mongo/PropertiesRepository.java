package com.ruby.cyclone.configserver.repo.mongo;

import com.ruby.cyclone.configserver.models.business.Property;
import com.ruby.cyclone.configserver.models.business.PropertyId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertiesRepository extends MongoRepository<Property, PropertyId> {


}
