package com.ruby.cyclone.configserver.repo.mongo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PropertiesRepoTest {

    @Autowired
    private PropertiesRepo propertiesRepo;


    @Test
    public void save() {

//        Property<String> property = new Property<>();
//
//        String key = "user.name";
//        property.setKey(key);
//        property.setValue("ruby");
//        propertiesRepo.save(property);
//        Optional<Property> byId = propertiesRepo.findById(key);
//        Assert.assertEquals(property, byId.get());

    }
}
