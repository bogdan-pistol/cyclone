package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Property;
import com.ruby.cyclone.configserver.models.business.PropsFile;
import com.ruby.cyclone.configserver.models.constants.FileFormat;
import com.ruby.cyclone.configserver.services.PropsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tenants/{tenant}/namespaces/{namespace}/apps/{app}")
public class FilesController {


    private PropsFileService propsFileService;

    @Autowired
    public FilesController(PropsFileService propsFileService) {
        this.propsFileService = propsFileService;
    }

    @GetMapping("/files")
    public Set<PropsFile> getFiles(@PathVariable String tenant, @PathVariable String namespace, @PathVariable String app) {
        return this.propsFileService.getFiles(tenant, namespace, app);
    }

    @PostMapping("/files")
    public String addFile(@PathVariable String tenant,
                          @PathVariable String namespace,
                          @PathVariable String app,
                          @RequestBody PropsFile propsFile) {
        return this.propsFileService.addFile(tenant, namespace, app, propsFile);
    }

    @PostMapping(value = "/files/import")
    public String importPropertiesFromFile(@PathVariable String tenant,
                                           @PathVariable String namespace,
                                           @PathVariable String app,
                                           @RequestParam FileFormat fileFormat,
                                           @RequestParam MultipartFile file) throws IOException {
        return this.propsFileService.importProperties(tenant, namespace, app, fileFormat, file);
    }


    @GetMapping(value = "/files/export/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    Resource exportFileByName(@PathVariable String tenant,
                              @PathVariable String namespace,
                              @PathVariable String app,
                              @PathVariable String filename) throws IOException {
        return propsFileService.exportFile(tenant, namespace, app, filename);

    }

    @GetMapping("/files/{file}/properties")
    public List<Property> listProperties(@PathVariable String tenant,
                                         @PathVariable String namespace,
                                         @PathVariable String app,
                                         @PathVariable String file) {
        return propsFileService.getPropertiesFromFile(tenant, namespace, app, file);

    }

}
