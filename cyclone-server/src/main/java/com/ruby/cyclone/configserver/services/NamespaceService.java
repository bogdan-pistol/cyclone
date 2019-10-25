package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NamespaceService {

    private NamespaceRepository namespaceRepository;

    @Autowired
    public NamespaceService(NamespaceRepository namespaceRepository) {
        this.namespaceRepository = namespaceRepository;
    }

    public List<String> getNamespaces() {
       return this.namespaceRepository.findAll()
                .stream()
                .map(Namespace::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    public Namespace addNamespace(Namespace namespace) {
        if (namespaceRepository.existsById(namespace.getName())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Namespace already exists.");
        }
        namespace.setApplications(Collections.emptySet());
        return namespaceRepository.save(namespace);
    }

    @Transactional
    public void archive(String namespace) {
        //todo make backup collection
        namespaceRepository.deleteById(namespace);
    }



}
