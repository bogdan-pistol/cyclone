package com.ruby.cyclone.configserver.services;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.ruby.cyclone.configserver.controllers.DummyValues.DUMMY_COUNTRIES;
import static com.ruby.cyclone.configserver.controllers.DummyValues.DUMMY_NS;

@Service
public class CountryService {
    public List<String> getBusinesses(String namespace) {
        if (DUMMY_NS.contains(namespace.toLowerCase())) {
            return DUMMY_COUNTRIES;
        }
        return Collections.emptyList();
    }

    public void archive(String country) {

    }
}
