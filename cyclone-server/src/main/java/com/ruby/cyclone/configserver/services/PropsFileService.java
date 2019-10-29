package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.*;
import com.ruby.cyclone.configserver.models.constants.FileFormat;
import com.ruby.cyclone.configserver.repo.mongo.ApplicationRepo;
import com.ruby.cyclone.configserver.repo.mongo.PropertiesRepo;
import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;

@Service
public class PropsFileService {

    private ApplicationRepo applicationRepo;
    private PropertiesRepo propertiesRepo;

    @Autowired
    public PropsFileService(ApplicationRepo applicationRepo, PropertiesRepo propertiesRepo) {
        this.applicationRepo = applicationRepo;
        this.propertiesRepo = propertiesRepo;
    }

    public Set<PropsFile> getFiles(String tenant, String namespaceId, String app) {
        AppId appId = AppId.builder().application(app)
                .namespace(NamespaceId.builder().tenant(tenant).namespace(namespaceId).build())
                .build();
        return applicationRepo.findById(appId).map(Application::getFiles)
                .orElse(Collections.emptySet());
    }

    @Transactional
    public String importProperties(String tenant, String namespace, String app, FileFormat fileFormat, MultipartFile file)
            throws IOException {

        Application application = getApplication(tenant, namespace, app);

        Set<PropsFile> files = application.getFiles();
        if (files == null) {
            files = new HashSet<>();
        }

        String originalFilename = file.getOriginalFilename();
        boolean fileExists = files.stream().anyMatch(f -> f.getName().equals(originalFilename));
        if (!fileExists) {
            files.add(new PropsFile(originalFilename));
            application.setFiles(files);
            applicationRepo.save(application);
        }

        InputStream is = file.getInputStream();
        Properties properties = new Properties();

        properties.load(is);
        List<Property> appProperties = new ArrayList<>();
        properties.forEach((k, v) -> {
            PropertyId id = PropertyId.builder().key(k.toString())
                    .tenant(tenant)
                    .namespace(namespace)
                    .application(app)
                    .file(originalFilename)
                    .build();
            Property property = new Property();
            property.setId(id);
            property.setValue(v);
            appProperties.add(property);
        });
        propertiesRepo.saveAll(appProperties);
        updateOtherAppsWithEmptyValue(application, appProperties);
        return originalFilename;


    }

    @Async
    public void updateOtherAppsWithEmptyValue(Application application, List<Property> appProperties) {
        List<Application> all = applicationRepo.findAll();
        all.stream()
                .filter(a -> a.getId() != application.getId())
                .forEach(a -> {
                    appProperties.forEach(property -> {
                        PropertyId id = property.getId();
                        id.setApplication(a.getId().getApplication());
                        id.setNamespace(a.getId().getNamespace().getNamespace());
                        id.setTenant(a.getId().getNamespace().getTenant());

                        if (!propertiesRepo.existsById(id)) {
                            property.setId(id);
                            property.setValue("");
                            propertiesRepo.save(property);
                        }
                    });
                });

    }

    public List<Property> getPropertiesFromFile(String tenant, String namespace, String country, String file) {
        return propertiesRepo.getPropertiesFromFile(tenant, namespace, country, file);
    }

    public Resource exportFile(String tenant, String namespace, String country, String filename) throws IOException {

        File tempDirectory = new File("./export");
        tempDirectory.mkdir();
        File tempFile = new File("./export/" + filename);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            Properties properties = readPropertiesFromDb(tenant, namespace, country, filename);
            properties.store(fos, "");
        }
        try (FileInputStream fis = new FileInputStream(tempFile)) {

            Resource resource = new ByteArrayResource(IOUtils.toByteArray(fis));
            if (resource.exists()) {
                return resource;
            } else {
                throw new RestException(HttpStatus.BAD_REQUEST, "File not found " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RestException(HttpStatus.BAD_REQUEST, "File not found " + filename);
        } finally {
            tempFile.delete();
            tempDirectory.delete();
        }
    }

    private Properties readPropertiesFromDb(String tenant, String namespace, String country, String filename) {
        List<Property> appProperties = propertiesRepo.getPropertiesFromFile(tenant, namespace, country, filename);

        Properties properties = new Properties();
        appProperties.forEach(p -> {
            properties.put(p.getId().getKey(), p.getValue());
        });
        return properties;
    }

    public String addFile(String tenant, String namespace, String app, PropsFile file) {
        Application application = getApplication(tenant, namespace, app);

        @UniqueElements Set<PropsFile> files = application.getFiles();
        if (files == null) {
            files = new HashSet<>();
        }
        files.add(file);
        application.setFiles(files);
        applicationRepo.save(application);
        return file.getName();
    }

    private Application getApplication(String tenant, String namespace, String app) {
        AppId appId = AppId.builder().application(app)
                .namespace(NamespaceId.builder().tenant(tenant).namespace(namespace).build())
                .build();
        return applicationRepo.findById(appId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Application not found " + appId));
    }
}
