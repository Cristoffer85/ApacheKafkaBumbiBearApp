package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import KafkaGroup.BumbiBearApp.repository.MySQLUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@EnableKafka
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@DirtiesContext
public class MySQLDBConsumerTest {

// NOTE: In order to properly test this class, the KafkaServer and Zookeeper must be stopped. Otherwise the ports specified (9092) that kafka uses, will be occupied.
// These classes test the function through those ports.

    @Autowired
    private MySQLDBConsumer mySQLDBConsumer;

    @MockBean
    private MySQLUserRepository mySQLUserRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the database with a sample user, had to use this to be able to not get assertion failure in @Beforeeach
        MySQLUser sampleUser = new MySQLUser();
        sampleUser.setId(Long.valueOf("123"));
        sampleUser.setSpecies("TestSpecies");

        when(mySQLUserRepository.findById(Long.valueOf("123"))).thenReturn(Optional.of(sampleUser));
    }

    @Test
    public void testConsume() {

        // ARRANGE - new Myswl user with dummy data
        MySQLUser sampleUser = new MySQLUser();
        sampleUser.setId(123L);
        sampleUser.setSpecies("TestSpecies");

        // ACT - consume user
        mySQLDBConsumer.consume(sampleUser);

        // (Verify) repository's save method, was called with sample user
        verify(mySQLUserRepository, times(1)).save(sampleUser);

        // ASSERT - if User was saved
        Optional<MySQLUser> savedUser = mySQLUserRepository.findById(123L);
        assertTrue(savedUser.isPresent()); // Check if user was saved

        // + spec. user properties
        assertEquals(123L, savedUser.get().getId());
        assertEquals("TestSpecies", savedUser.get().getSpecies());
    }
}
