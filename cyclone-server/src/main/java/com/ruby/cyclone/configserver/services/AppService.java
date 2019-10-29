package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.AppId;
import com.ruby.cyclone.configserver.models.business.Application;
import com.ruby.cyclone.configserver.models.business.NamespaceId;
import com.ruby.cyclone.configserver.repo.mongo.ApplicationRepo;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AppService {

    public static final RestException NAMESPACE_NOT_FOUND = new RestException(HttpStatus.NOT_FOUND, "Namespace or tenant does not exist");
    private NamespaceRepo namespaceRepo;
    private ApplicationRepo applicationRepo;

    @Autowired
    public AppService(NamespaceRepo namespaceRepo,
                      ApplicationRepo applicationRepo) {
        this.namespaceRepo = namespaceRepo;
        this.applicationRepo = applicationRepo;
    }

    public Set<Application> getApps(String tenant, String namespace) {
        return applicationRepo.findAllByNamespace(tenant, namespace);
    }


    public void archive(String tenant, String namespace, String application) {
        if (!namespaceRepo.existsById(NamespaceId.builder().namespace(namespace).tenant(tenant).build())) {
            throw NAMESPACE_NOT_FOUND;
        }
        AppId id = AppId.builder().application(application)
                .namespace(NamespaceId.builder()
                        .tenant(tenant)
                        .namespace(namespace)
                        .build()).build();
        applicationRepo.deleteById(id);
    }

    public Application addApp(String tenant, String namespace, Application requestApplication) {
        NamespaceId namespaceId = NamespaceId.builder().tenant(tenant)
                .namespace(namespace).build();
        if (!namespaceRepo.existsById(namespaceId)) {
            throw NAMESPACE_NOT_FOUND;
        }
        AppId id = requestApplication.getId();
        id.setApplication(requestApplication.getName());
        id.setNamespace(namespaceId);
        return applicationRepo.save(requestApplication);
    }
}
