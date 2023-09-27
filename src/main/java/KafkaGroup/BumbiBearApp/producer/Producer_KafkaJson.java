package KafkaGroup.BumbiBearApp.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class Producer_KafkaJson {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer_KafkaJson.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public Producer_KafkaJson(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Object data) {
        LOGGER.info(String.format("Message sent -> %s", data.toString()));
        Message<Object> message = MessageBuilder.withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message);
    }
}

