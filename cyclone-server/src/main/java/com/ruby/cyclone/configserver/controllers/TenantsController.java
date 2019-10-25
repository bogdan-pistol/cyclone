package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Tenant;
import com.ruby.cyclone.configserver.repo.mongo.TenantRepository;
import com.ruby.cyclone.configserver.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/tenants")
public class TenantsController {

    private final TenantRepository tenantRepository;
    private final TenantService tenantService;

    @Autowired
    public TenantsController(TenantRepository tenantRepository, TenantService tenantService) {
        this.tenantRepository = tenantRepository;
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<Tenant> getTenants() {
        return tenantRepository.findAll();
    }

    @GetMapping("/{tenantId}")
    public Optional<Tenant> getTenant(@PathVariable("tenantId") String tenantId) {
        return tenantRepository.findById(tenantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tenant createTenant(@Valid @RequestBody Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @PutMapping("/{tenantId}/namespaces/{namespaceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addTenantNamespace(@PathVariable("tenantId") String tenantId, @PathVariable("namespaceId") String namespaceId) {
        tenantService.addTenantNamespace(tenantId, namespaceId);
    }

    @PutMapping("/{tenantId}")
    public Tenant updateTenant(@PathVariable("tenantId") String tenantId, @Valid @RequestBody Tenant tenant) {
        tenant.setId(tenantId);
        return tenantRepository.save(tenant);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTenant(@PathVariable("tenantId") String tenantId) {
        tenantRepository.deleteById(tenantId);
    }

}
