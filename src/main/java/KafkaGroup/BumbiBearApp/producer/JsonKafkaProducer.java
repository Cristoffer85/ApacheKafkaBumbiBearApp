package KafkaGroup.BumbiBearApp.producer;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger((JsonKafkaProducer.class));
    private static KafkaTemplate<String, MySQLUser> kafkaTemplate;
    public JsonKafkaProducer(KafkaTemplate<String, MySQLUser> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public static void sendMessage(MySQLUser data) {
        LOGGER.info(String.format("Message sent -> %s", data.toString()));
        Message<MySQLUser> message =

                MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, "javaguides_json").build();

        kafkaTemplate.send(message);
    }
}
