package com.ruby.cyclone.configserver.controllers;

import java.util.Arrays;
import java.util.List;

//TODO replaces these values with services
public interface DummyValues {
    static final List<String> DUMMY_NS = Arrays.asList("test", "qualif", "integration", "prod");
    static final List<String> DUMMY_COUNTRIES = Arrays.asList("oro", "ofr", "me");

}
