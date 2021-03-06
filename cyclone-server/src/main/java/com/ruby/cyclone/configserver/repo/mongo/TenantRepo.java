package com.ruby.cyclone.configserver.repo.mongo;

import com.ruby.cyclone.configserver.models.business.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TenantRepo extends MongoRepository<Tenant, String> {
    List<Tenant> findByName();
}
