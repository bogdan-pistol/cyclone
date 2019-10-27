package com.ruby.cyclone.configserver.repo.mongo;

import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.models.business.NamespaceId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NamespaceRepo extends MongoRepository<Namespace, NamespaceId> {

    @Query("{'_id.tenant': ?0}")
    List< Namespace> findAllByTenant(String tenant);
}
