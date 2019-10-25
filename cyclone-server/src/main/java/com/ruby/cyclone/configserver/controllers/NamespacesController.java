package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.models.business.Tenant;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepository;
import com.ruby.cyclone.configserver.services.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/namespaces")
public class NamespacesController {

    private final NamespaceRepository namespaceRepository;
    private final NamespaceService namespaceService;

    @Autowired
    public NamespacesController(NamespaceRepository namespaceRepository, NamespaceService namespaceService) {
        this.namespaceRepository = namespaceRepository;
        this.namespaceService = namespaceService;
    }

    @GetMapping
    public List<String> getNamespaces() {
        return namespaceService.getNamespaces();
    }

    @GetMapping("/{namespace}")
    public Optional<Namespace> getTenant(@PathVariable("namespace") String namespace) {
        return namespaceRepository.findById(namespace);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Namespace createNamespace(@Valid @RequestBody Namespace namespace) {
        return namespaceService.addNamespace(namespace);
    }

    @DeleteMapping("/{namespace}/archive")
    public void archive(@PathVariable String namespace) {
        namespaceService.archive(namespace);
    }

}
