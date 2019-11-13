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
public class PropertiesRepoTest {

    @Autowired
    private PropertiesRepo propertiesRepo;


    @Test
    public void save() {

        Property<String> property = new Property<>();

        PropertyId id = PropertyId.builder().namespace("ns")
                .key("key")
                .file("file").build();
        String key = "user.name";
        property.setValue(key);
        property.setId(id);
        property.setValue("ruby");
        propertiesRepo.save(property);
        Optional<Property> byId = propertiesRepo.findById(id);
        Assert.assertEquals(property, byId.get());

    }
}
