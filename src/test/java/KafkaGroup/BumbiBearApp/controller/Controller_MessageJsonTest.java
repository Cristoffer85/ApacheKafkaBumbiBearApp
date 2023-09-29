package KafkaGroup.BumbiBearApp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class Controller_MessageJsonTest {

// NOTE: In order to properly test this class, the KafkaServer and Zookeeper must be running in the background to be able to test the API send function through it.

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testPublishEndpoint() throws Exception {
        // Prepare a sample JSON request body
        String jsonRequest = "{\"id\": 1, \"username\": \"testUser\"}";

        // Send a POST request to the /api/v1/kafka/publish endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/kafka/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("[Json] Message sent to Kafka Topic"))
                .andReturn();
    }
}
