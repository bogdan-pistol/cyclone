package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Tenant;
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

    private final TenantService tenantService;

    @Autowired
    public TenantsController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<Tenant> getTenants() {
        return tenantService.findAll();
    }

    @GetMapping("/{tenantId}")
    public Optional<Tenant> getTenant(@PathVariable("tenantId") String tenantId) {
        return tenantService.findById(tenantId);
    }

    @PostMapping
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantService.save(tenant);
    }

    @PutMapping("/{tenantId}")
    public Tenant updateTenant(@PathVariable("tenantId") String tenantId, @Valid @RequestBody Tenant tenant) {
        tenant.setId(tenantId);
        return tenantService.save(tenant);
    }

    @DeleteMapping("/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTenant(@PathVariable("tenantId") String tenantId) {
        tenantService.deleteById(tenantId);
    }

//    @GetMapping
//    public Map<String, Application> getApplicationsGroupedByNs(@PathVariable String tenant) {
//        return this.tenantService.getApplicationsGroupedByNs(tenant);
//    }

}
