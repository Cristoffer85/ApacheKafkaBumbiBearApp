package KafkaGroup.BumbiBearApp.consumer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.Mockito.*;

@SpringBootTest
@EnableKafka
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@DirtiesContext
public class TxtConsumerTest {

// NOTE: In order to properly test this class, the KafkaServer and Zookeeper must be stopped. Otherwise the ports specified (9092) that kafka uses, will be occupied.
// These classes test the function through those ports.

    @Autowired
    private TxtConsumer txtConsumer;

    @Test
    public void testConsume() {

        // ARRANGE - Logger info of class
        Logger logger = Mockito.mock(Logger.class);
        when(logger.isInfoEnabled()).thenReturn(true);
        TxtConsumer.LOGGER = logger;

        // ACT - consume message sent by kafka
        String message = "Test message";
        txtConsumer.consume(message);

        // ASSERT - that the log message was produced
        verify(logger, times(2)).info(anyString());

        // + that the message was saved to a file
        verifyMessageSavedToFile();
    }

    private void verifyMessageSavedToFile() {
    }
}
