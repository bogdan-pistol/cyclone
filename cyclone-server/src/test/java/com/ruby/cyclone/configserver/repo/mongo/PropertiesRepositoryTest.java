package com.ruby.cyclone.configserver.repo.mongo;


import com.ruby.cyclone.configserver.models.business.Property;
import com.ruby.cyclone.configserver.models.business.PropertyId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PropertiesRepositoryTest {

    @Autowired
    private PropertiesRepository propertiesRepository;


    @Test
    public void save() {

        Property<String> property = new Property<>();
        PropertyId id = PropertyId.builder()
                .key("user.name")
                .file("application.properties")
                .country("ORO")
                .namespace("TEST")
                .build();
        property.setId(id);
        property.setValue("ruby");
        propertiesRepository.save(property);
        Optional<Property> byId = propertiesRepository.findById(id);
        Assert.assertEquals(property, byId.get());

    }
}
