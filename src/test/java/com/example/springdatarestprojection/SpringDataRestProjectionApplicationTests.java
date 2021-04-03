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
               .andExpect(jsonPath("_embedded.persons[0].address.street").value("Secret Street"))
               .andExpect(status().isOk())
               .andDo(print());
    }

}
