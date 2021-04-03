package com.example.springdatarestprojection;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringDataRestProjectionApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getAllPersons() {

        mockMvc.perform(get("/persons"))
               .andExpect(jsonPath("_embedded.persons").isArray())
               .andExpect(jsonPath("_embedded.persons", hasSize(1)))
               .andExpect(jsonPath("_embedded.persons[0].name").value("Alice"))
               .andExpect(jsonPath("_embedded.persons[0].address").doesNotExist())
               .andExpect(jsonPath("_embedded.persons[0]._links.address").exists())
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    @SneakyThrows
    void getOnePerson_streetIsNotIncluded() {

        mockMvc.perform(get("/persons/2"))
               .andExpect(jsonPath("name").value("Alice"))
               .andExpect(jsonPath("address").doesNotExist())
               .andExpect(jsonPath("_links.address").exists())
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    @SneakyThrows
    void getOnePersonWithProjection_streetIsIncluded() {

        mockMvc.perform(get("/persons/2?projection=includeAddress"))
               .andExpect(jsonPath("name").value("Alice"))
               .andExpect(jsonPath("address").exists())
               .andExpect(jsonPath("address.street").value("Secret Street"))
               .andExpect(jsonPath("_links.address").exists())
               .andExpect(status().isOk())
               .andDo(print());
    }

}
