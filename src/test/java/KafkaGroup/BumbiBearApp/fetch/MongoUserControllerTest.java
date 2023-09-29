package KafkaGroup.BumbiBearApp.fetch;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MongoUserControllerTest {

// NOTE: In order to properly test this class, the KafkaServer and Zookeeper must be running in the background to be able to test the API send function through it.

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MongoService mongoService;

    @Test
    public void testGetAllMongoUsers() throws Exception {
        // ARRANGE - Dummy user
        List<MongoUser> sampleUsers = new ArrayList<>();

        when(mongoService.getAllMongoUsers()).thenReturn(sampleUsers);

        // ACT - Perform a GET request to the controller endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/mongo-users"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andReturn();

        // ASSERT - that the controller calls the MongoService method
        verify(mongoService, times(1)).getAllMongoUsers();
    }
}
