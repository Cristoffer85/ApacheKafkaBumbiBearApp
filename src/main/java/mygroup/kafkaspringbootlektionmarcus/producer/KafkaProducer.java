package mygroup.kafkaspringbootlektionmarcus.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//Klass som ansvarar för EN producer
//LOGGER = Skriver ut/Loggar det som händer i kommunikationen med Kafka, in till IntelliJ

@Service
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    // Konstruktor
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){this.kafkaTemplate = kafkaTemplate;}

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
        LOGGER.info(String.format("Message sent -> %s", message));
    }
}