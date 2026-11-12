package com.qiao.usermanagement.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUser() throws Exception {

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.email").value("david@example.com"))
                .andExpect(jsonPath("$.age").value(45));
    }

    @Test
    void testCreateUser() throws Exception {

        String requestBody = """
                {
                "id":1,
                "name":"David",
                "email":"david@example.com",
                "age":45
                }
                """;

        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.email").value("david@example.com"))
                .andExpect(jsonPath("$.age").value(45));
    }

    @Test
    void testUpdateUser() throws Exception {

        String requestBody = """
                {
                "id":1,
                "name":"David Qiao",
                "email":"david@example.com",
                "age":46
                }
                """;

        mockMvc.perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("David Qiao"))
                .andExpect(jsonPath("$.age").value(46));
    }

    @Test
    void testDeleteUser() throws Exception {

        mockMvc.perform(
                        delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFailValidation() throws Exception {

        String requestBody = """
                {
                "id":1,
                "name":"",
                "email":"david@example.com",
                "age":46
                }
                """;

        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
