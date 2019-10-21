package com.ruby.cyclone.configserver.repo.mongo;

import com.ruby.cyclone.configserver.models.business.Namespace;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NamespaceRepository extends MongoRepository<Namespace, String> {
}
