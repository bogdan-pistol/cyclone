package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.exceptions.RestException;
import com.ruby.cyclone.configserver.models.business.*;
import com.ruby.cyclone.configserver.models.constants.FileFormat;
import com.ruby.cyclone.configserver.repo.mongo.NamespaceRepository;
import com.ruby.cyclone.configserver.repo.mongo.PropertiesRepository;
import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileService {

    private NamespaceRepository namespaceRepository;

    private PropertiesRepository propertiesRepository;

    @Autowired
    public FileService(NamespaceRepository namespaceRepository, PropertiesRepository propertiesRepository) {
        this.namespaceRepository = namespaceRepository;
        this.propertiesRepository = propertiesRepository;
    }

    public List<String> getFiles(String namespaceId, String countryId) {

        Optional<Namespace> namespaceDao = this.namespaceRepository.findById(namespaceId);
        return namespaceDao.map(Namespace::getCountries)
                .flatMap(countries -> countries.stream().filter(c -> c.equals(countryId)).findFirst())
                .map(Country::getFiles)
                .orElse(Collections.emptySet())
                .stream()
                .map(FileName::getName).collect(Collectors.toList());

    }

    @Transactional
    public String importProperties(String namespace, String country, FileFormat fileFormat, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();

        Optional<Namespace> optionalNamespace = namespaceRepository.findById(namespace);
        Namespace namespaceFromDb = optionalNamespace.orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to get namespace."));
        Set<Country> countries = namespaceFromDb.getCountries();


        Country country1 = countries
                .stream()
                .filter(c -> c.getId().equals(country))
                .findFirst()
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to find country."));

        @UniqueElements Set<FileName> files = country1.getFiles();
        if (files == null) {
            files = new HashSet<>();
        }
        boolean fileExists = files.stream().anyMatch(f -> f.getName().equals(file.getOriginalFilename()));
        if (!fileExists) {
            files.add(new FileName(file.getOriginalFilename()));
            country1.setFiles(files);
            namespaceFromDb.setCountries(countries);
            namespaceRepository.save(namespaceFromDb);
        }

        InputStream is = file.getInputStream();
        Properties properties = new Properties();
        properties.load(is);
        List<Property> appProperties = new ArrayList<>();
        properties.forEach((k, v) -> {
            PropertyId id = PropertyId.builder().key(k.toString())
                    .namespace(namespace)
                    .country(country)
                    .file(file.getOriginalFilename())
                    .build();
            Property property = new Property();
            property.setId(id);
            property.setValue(v);
            appProperties.add(property);
        });
        propertiesRepository.saveAll(appProperties);
        return originalFilename;


    }

    public List<Property> getPropertiesFromFile(String namespace, String country, String file) {
        return propertiesRepository.getPropertiesFromFile(namespace, country, file);
    }

    public Resource exportFile(String namespace, String country, String filename) throws IOException {

        File tempDirectory = new File("./export");
        tempDirectory.mkdir();
        File tempFile = new File("./export/" + filename);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            Properties properties = readPropertiesFromDb(namespace, country, filename);
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

    private Properties readPropertiesFromDb(String namespace, String country, String filename) {
        List<Property> appProperties = propertiesRepository.getPropertiesFromFile(namespace, country, filename);

        Properties properties = new Properties();
        appProperties.forEach(p -> {
            properties.put(p.getId().getKey(), p.getValue());
        });
        return properties;
    }

    public String addFile(String namespaceId, String countryId, String file) {
        Optional<Namespace> namespace = this.namespaceRepository.findById(namespaceId);
        return namespace.flatMap(ns -> {
            Set<Country> countries = ns.getCountries();
            return countries.stream().filter(c -> c.getId().equals(countryId)).findFirst()
                    .flatMap(c -> {
                        Set<FileName> files = c.getFiles();
                        if (files == null) {
                            files = new HashSet<>();
                        }
                        files.add(new FileName(file));
                        c.setFiles(files);
                        ns.setCountries(countries);
                        namespaceRepository.save(ns);
                        return Optional.of(file);
                    });
        }).orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "Error trying to add file."));
    }
}
