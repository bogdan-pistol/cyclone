package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.models.constants.FileFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.ruby.cyclone.configserver.controllers.DummyValues.DUMMY_NS;

@Service
//TODO replace with country logic this dummy methods
public class FileService {

    public List<String> getFiles(String namespace, String country) {
        if (DUMMY_NS.contains(namespace.toLowerCase()) && DUMMY_NS.contains(country.toLowerCase())) {
            return Arrays.asList("application.properties", "datastore.properties");
        }
        return Collections.emptyList();    }

    public String importProperties(String namespace, String country, FileFormat fileFormat, MultipartFile file) {
        return  UUID.randomUUID().toString();
    }

    public Map<String, Object> getPropertiesFromFile(String namespace, String country, String file) {
        Map<String, Object> props = new HashMap<>();
        for (int i = 0; i < (namespace + country + file).length() * new Random().nextInt(3); i++) {
            props.put(file + "_name", "Un nume Frumos" + namespace + country);
        }
        return props;
    }

    public void exportFile(String namespace, String country, String filename) {

    }
}
