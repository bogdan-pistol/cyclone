package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.models.business.Tenant;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepository;
import com.ruby.cyclone.configserver.repo.mongo.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private NamespaceRepository namespaceRepository;

    public List<Tenant> getTenants() {
        return tenantRepository.findAll();
    }

    public void addTenantNamespace(String tenantId, String namespaceId) {

        Optional<Tenant> optionalTenant = tenantRepository.findById(tenantId);

        if(!optionalTenant.isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Tenant not found!");
        }

        Optional<Namespace> optionalNamespace = namespaceRepository.findById(namespaceId);

        if (!optionalNamespace.isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Namespace not found!");
        }

        Tenant tenant = optionalTenant.get();
        Namespace namespace = optionalNamespace.get();
        namespace.setTenant(tenant);
        namespaceRepository.save(namespace);

        List<Namespace> tenantNamespaces = tenant.getNamespaces();

        if (tenantNamespaces == null) {
            tenantNamespaces = new ArrayList<>();
        }

        tenantNamespaces.add(namespace);
        tenant.setNamespaces(tenantNamespaces);
        tenantRepository.save(tenant);
    }
}
