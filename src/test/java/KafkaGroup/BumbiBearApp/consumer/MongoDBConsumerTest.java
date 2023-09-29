package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import KafkaGroup.BumbiBearApp.repository.MongoUserRepository;
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
public class MongoDBConsumerTest {

// NOTE: In order to properly test this class, the KafkaServer and Zookeeper must be stopped. Otherwise the ports specified (9092) that kafka uses, will be occupied.
// These classes test the function through those ports.

    @Autowired
    private MongoDBConsumer mongoDBConsumer;

    @MockBean
    private MongoUserRepository mongoUserRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the database with a sample user, had to use this to be able to not get assertion failure in @Beforeeach
        MongoUser sampleUser = new MongoUser();
        sampleUser.setId("123");
        sampleUser.setSpecies("TestSpecies");

        // Mock the repository's behavior to return the sample user when findById is called
        when(mongoUserRepository.findById("123")).thenReturn(Optional.of(sampleUser));
    }
    @Test
    public void testConsume() {

        // ARRANGE - dummy mongoUser instantiated
        MongoUser sampleUser = new MongoUser();
        sampleUser.setId("123");
        sampleUser.setSpecies("TestSpecies");

        // ACT - consume dummy mongoUser
        mongoDBConsumer.consume(sampleUser);

        // (Verify) repository's save method, was called with sample user
        verify(mongoUserRepository, times(1)).save(sampleUser);

        // ASSERT - if User was saved
        Optional<MongoUser> savedUser = mongoUserRepository.findById("123");
        assertTrue(savedUser.isPresent());

        // + spec. user properties
        assertEquals("123", savedUser.get().getId());
        assertEquals("TestSpecies", savedUser.get().getSpecies());
    }
}







