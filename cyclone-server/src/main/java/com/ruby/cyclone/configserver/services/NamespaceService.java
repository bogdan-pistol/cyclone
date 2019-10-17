package com.ruby.cyclone.configserver.services;

import com.ruby.cyclone.configserver.models.api.request.AddNamespaceRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.ruby.cyclone.configserver.controllers.DummyValues.DUMMY_NS;

@Service
public class NamespaceService {
    public List<String> getNamespaces() {
        return DUMMY_NS;
    }

    public String addNamespace(AddNamespaceRequest namespaceRequest) {
        return UUID.randomUUID().toString();
    }

    public void archive() {

    }
}
