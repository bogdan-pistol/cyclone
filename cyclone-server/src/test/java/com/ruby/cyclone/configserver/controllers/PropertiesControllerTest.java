package com.ruby.cyclone.configserver.controllers;

import com.ruby.cyclone.configserver.services.PropertiesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(PropertiesController.class)
public class PropertiesControllerTest {

    public static final String COUNTRY = "ORO";
    public static final String NAMESPACE = "test";
    public static final String KEY_WORD = "user.name";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertiesService propertiesService;

    @Test
    public void test() throws Exception {
//        Property<String> stringProperty = new Property<>();
//        stringProperty.setKey(KEY_WORD);
//        stringProperty.setValue("ruby");
//        List<Namespace> namespaces = singletonList(Namespace.builder()
//                .name(NAMESPACE)
//                .build());
//
//        Mockito.when(propertiesService.searchProperties(NAMESPACE, COUNTRY, KEY_WORD))
//                .thenReturn(namespaces);
//
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/properties/search")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .param("namespace", NAMESPACE)
//                .param("application", COUNTRY)
//                .param("key", KEY_WORD)
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$", hasSize(namespaces.size())))
//                .andExpect(jsonPath("$[0].name", is(namespaces.get(0).getName())));

    }

}
