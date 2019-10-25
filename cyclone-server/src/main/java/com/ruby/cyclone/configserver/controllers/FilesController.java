package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.models.business.Property;
import com.ruby.cyclone.configserver.models.constants.FileFormat;
import com.ruby.cyclone.configserver.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tenants/{tenant}/namespaces/{namespace}/apps/{app}")
public class FilesController {


    private FileService fileService;

    @Autowired
    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/files")
    public List<String> getFiles(@PathVariable String namespace, @PathVariable String application) {
        return this.fileService.getFiles(namespace, application);
    }

    @PostMapping("/files/{file}")
    public String addFile(@PathVariable String namespace, @PathVariable String application, @PathVariable String file) {
        return this.fileService.addFile(namespace, application, file);
    }

    @PostMapping(value = "/files/import")
    public String importPropertiesFromFile(@PathVariable String tenant,
                                           @PathVariable String namespace,
                                           @PathVariable String application,
                                           @RequestParam FileFormat fileFormat,
                                           @RequestParam MultipartFile file) throws IOException {
        return this.fileService.importProperties(namespace, application, fileFormat, file);
    }


    @GetMapping(value = "/files/export/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    Resource exportFileByName(@PathVariable String tenant,
                              @PathVariable String namespace,
                              @PathVariable String application,
                              @PathVariable String filename) throws IOException {
        return fileService.exportFile(tenant, namespace, application, filename);

    }

    @GetMapping("/files/{file}/properties")
    public List<Property> listProperties(@PathVariable String tenant,
                                         @PathVariable String namespace,
                                         @PathVariable String country,
                                         @PathVariable String file) {
        return fileService.getPropertiesFromFile(tenant, namespace, country, file);

    }

}
