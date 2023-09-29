package KafkaGroup.BumbiBearApp.producer;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableKafka
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@DirtiesContext
public class Producer_KafkaJsonTest {

// NOTE: In order to properly test this class, the KafkaServer and Zookeeper must be stopped. Otherwise the ports specified (9092) that kafka uses, will be occupied.
// These classes test the function through those ports.

    @Autowired
    private Producer_KafkaJson producer;

    private Logger logger;
    private ListAppender<ch.qos.logback.classic.spi.ILoggingEvent> listAppender;

    @BeforeEach
    public void setupLogger() {
        logger = (Logger) LoggerFactory.getLogger(Producer_KafkaJson.class);

        listAppender = new ListAppender<>();
        listAppender.start();

        logger.addAppender(listAppender);
    }

    @Test
    public void testSendMessage() {

        // ARRANGE - Sample data to send
        String topic = "test_topic";
        Object data = "Test message";

        // ACT - send message
        producer.sendMessage(topic, data);

        // ASSERT - Verify log message produced
        List<ch.qos.logback.classic.spi.ILoggingEvent> logEvents = listAppender.list;
        assertEquals(1, logEvents.size());

        // + message sent to kafka
        ch.qos.logback.classic.spi.ILoggingEvent logEvent = logEvents.get(0);
        assertEquals("Message sent -> " + data.toString(), logEvent.getMessage());
    }
}

