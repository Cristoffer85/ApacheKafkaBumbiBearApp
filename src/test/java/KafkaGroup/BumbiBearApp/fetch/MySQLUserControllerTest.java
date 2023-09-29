package KafkaGroup.BumbiBearApp.fetch;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
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
public class MySQLUserControllerTest {

// NOTE: In order to properly test this class, the KafkaServer and Zookeeper must be running in the background to be able to test the API send function through it.

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MySQLService mySQLService;

    @Test
    public void testGetAllMySQLUsers() throws Exception {
        // ARRANGE - Dummy user
        List<MySQLUser> sampleUsers = new ArrayList<>();

        when(mySQLService.getAllMySQLUsers()).thenReturn(sampleUsers);

        // ACT - Perform a GET request to the controller endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/mysql-users"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andReturn();

        // ASSERT - that the controller calls the MySQLService method
        verify(mySQLService, times(1)).getAllMySQLUsers();
    }
}
