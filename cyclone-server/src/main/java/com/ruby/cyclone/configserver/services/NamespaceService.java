package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.models.business.NamespaceId;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepo;
import com.ruby.cyclone.configserver.repo.mongo.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NamespaceService {

    private NamespaceRepo namespaceRepo;

    private TenantRepo tenantRepo;

    @Autowired
    public NamespaceService(NamespaceRepo namespaceRepo, TenantRepo tenantRepo) {
        this.tenantRepo = tenantRepo;
        this.namespaceRepo = namespaceRepo;
    }

    public List<Namespace> getNamespacesByTenant(String tenant) {
        return this.namespaceRepo.findAllByTenant(tenant)
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional
    public Namespace addNamespace(String tenant, Namespace namespace) {
        if (!tenantRepo.existsById(tenant)) {
            throw new RestException(HttpStatus.NOT_FOUND,
                    "This tenant doesn't exists, " + tenant);
        }
        namespace.getId().setTenant(tenant);
        namespace.getId().setNamespace(namespace.getName());
        if (namespaceRepo.existsById(namespace.getId())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Namespace already exists.");
        }
        return namespaceRepo.save(namespace);
    }

    @Transactional
    public void archive(NamespaceId id) {
        //todo make backup collection
        namespaceRepo.deleteById(id);
    }


    public Optional<Namespace> findById(NamespaceId id) {
        return namespaceRepo.findById(id);
    }
}
