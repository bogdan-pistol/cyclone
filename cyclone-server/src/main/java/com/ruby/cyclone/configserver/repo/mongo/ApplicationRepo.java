package com.ruby.cyclone.configserver.repo.mongo;

import com.ruby.cyclone.configserver.models.business.AppId;
import com.ruby.cyclone.configserver.models.business.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ApplicationRepo extends MongoRepository<Application, AppId> {

    @Query("{'_id.namespace.tenant': ?0, '_id.namespace.namespace': ?1}")
    Set<Application> findAllByNamespace(String tenant, String namespace);

}
