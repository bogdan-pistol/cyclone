package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Application;
import com.ruby.cyclone.configserver.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tenants/{tenant}/namespaces/{namespace}/apps")
public class AppController {

    private AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping()
    public Set<Application> getApps(@PathVariable String tenant, @PathVariable String namespace) {
        return appService.getApps(tenant, namespace);
    }

    @PostMapping()
    public Application addApp(@PathVariable String tenant,
                              @PathVariable String namespace,
                              @RequestBody Application application) {
        return appService.addApp(tenant, namespace, application);
    }

    @DeleteMapping("/{app}/archive")
    public void archive(@PathVariable String tenant, @PathVariable String namespace, @PathVariable String app) {
        this.appService.archive(tenant, namespace, app);
    }

}
