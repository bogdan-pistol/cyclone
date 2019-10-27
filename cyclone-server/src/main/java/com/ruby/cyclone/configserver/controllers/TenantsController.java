package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Tenant;
import com.ruby.cyclone.configserver.repo.mongo.TenantRepo;
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

    private final TenantRepo tenantRepo;;

    @Autowired
    public TenantsController(TenantRepo tenantRepo) {
        this.tenantRepo = tenantRepo;
    }

    @GetMapping
    public List<Tenant> getTenants() {
        return tenantRepo.findAll();
    }

    @GetMapping("/{tenantId}")
    public Optional<Tenant> getTenant(@PathVariable("tenantId") String tenantId) {
        return tenantRepo.findById(tenantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tenant createTenant(@Valid @RequestBody Tenant tenant) {
        return tenantRepo.save(tenant);
    }

    @PutMapping("/{tenantId}")
    public Tenant updateTenant(@PathVariable("tenantId") String tenantId, @Valid @RequestBody Tenant tenant) {
        tenant.setId(tenantId);
        return tenantRepo.save(tenant);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTenant(@PathVariable("tenantId") String tenantId) {
        tenantRepo.deleteById(tenantId);
    }

}
