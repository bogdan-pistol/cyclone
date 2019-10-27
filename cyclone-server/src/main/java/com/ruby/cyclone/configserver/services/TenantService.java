package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.models.business.Tenant;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepo;
import com.ruby.cyclone.configserver.repo.mongo.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private NamespaceRepo namespaceRepo;

    public List<Tenant> getTenants() {
        return tenantRepo.findAll();
    }

}
