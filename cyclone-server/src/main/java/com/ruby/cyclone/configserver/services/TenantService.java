package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.models.business.Application;
import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.models.business.Tenant;
import com.ruby.cyclone.configserver.repo.mongo.ApplicationRepo;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepo;
import com.ruby.cyclone.configserver.repo.mongo.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenantService {

    private TenantRepo tenantRepo;

    private ApplicationRepo applicationRepo;

    @Autowired
    public TenantService(TenantRepo tenantRepo, ApplicationRepo applicationRepo) {
        this.tenantRepo = tenantRepo;
        this.applicationRepo = applicationRepo;
    }

    public List<Tenant> getTenants() {
        return tenantRepo.findAll();
    }

    public List<Tenant> findAll() {
        return tenantRepo.findAll();
    }

    public Optional<Tenant> findById(String tenantId) {
        return tenantRepo.findById(tenantId);
    }

    public Tenant save(Tenant tenant) {
        return this.tenantRepo.save(tenant);
    }

    public void deleteById(String tenantId) {
        this.tenantRepo.deleteById(tenantId);
    }

//    public Map<String, Application> getApplicationsGroupedByNs(String tenant) {
//        List<Namespace> namespaces = namespaceRepo.findAllByTenant(tenant);
//        namespaces.stream()
//                .map(n -> applicationRepo.findAllByNamespace(tenant, n.getId().getNamespace()))
//                .
//    }
}
