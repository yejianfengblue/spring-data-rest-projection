package com.example.springdatarestprojection;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringDataRestProjectionApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void getAllPersons() {

        mockMvc.perform(get("/persons"))
               .andExpect(jsonPath("_embedded.persons").isArray())
               .andExpect(jsonPath("_embedded.persons", hasSize(1)))
               .andExpect(jsonPath("_embedded.persons[0].name").value("Alice"))
               .andExpect(jsonPath("_embedded.persons[0].address.street").value("Secret Street"))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    @SneakyThrows
    void createPerson() {

        HashMap<String, Object> addressJson = new HashMap<>();
        addressJson.put("street", "T Street");

        HashMap<String, Object> personJson = new HashMap<>();
        personJson.put("name", "Tim");
        personJson.put("address", addressJson);

        mockMvc.perform(post("/persons")
                                .content(objectMapper.writeValueAsString(personJson))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andDo(print());
    }

}
