package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Application;
import com.ruby.cyclone.configserver.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/namespaces/{namespace}/apps")
public class ApplicationsController {

    private ApplicationService applicationService;

    @Autowired
    public ApplicationsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping()
    public Set<Application> getApplications(@PathVariable String namespace) {
        return applicationService.getApplications(namespace);
    }

    @PostMapping()
    public Application addApp(@PathVariable String namespace, @RequestBody Application application) {
        return applicationService.addApp(namespace, application);
    }

    @DeleteMapping("/{app}/archive")
    public void archive(@PathVariable String namespace, @PathVariable String app) {
        this.applicationService.archive(namespace, app);
    }

}
