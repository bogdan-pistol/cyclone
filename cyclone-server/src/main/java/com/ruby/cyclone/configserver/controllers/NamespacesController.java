package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Namespace;
import com.ruby.cyclone.configserver.models.business.NamespaceId;
import com.ruby.cyclone.configserver.services.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/tenants/{tenant}/namespaces")
public class NamespacesController {

    private final NamespaceService namespaceService;

    @Autowired
    public NamespacesController(NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    @GetMapping
    public List<NamespaceId> getNamespaces(@PathVariable String tenant) {
        return namespaceService.getNamespacesByTenant(tenant);
    }

    @GetMapping("/{namespace}")
    public Optional<Namespace> getNamespace(@PathVariable String tenant, @PathVariable("namespace") String namespace) {
        return namespaceService.findById(NamespaceId.builder().tenant(tenant).namespace(namespace).build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Namespace createNamespace(@Valid @RequestBody Namespace namespace) {
        return namespaceService.addNamespace(namespace);
    }

    @DeleteMapping("/{namespace}/archive")
    public void archive(@PathVariable String tenant, @PathVariable String namespace) {
        namespaceService.archive(NamespaceId.builder().namespace(namespace).tenant(tenant).build());
    }

}
