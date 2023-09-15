package mygroup.kafkaspringbootlektionmarcus.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class KafkaWebConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWebConsumer.class);

    @KafkaListener(topics = "myString_Topic", groupId = "String_Listener")
    public void consume(String message) {
        LOGGER.info(String.format("Message received -> %s", message));

        // Save the message to an external .txt file
        saveMessageToFile(message);
    }

    private void saveMessageToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rec_Stringmessages_mygroup1.txt", true))) {
            writer.println(message);
        } catch (IOException e) {
            LOGGER.error("Error saving message to file", e);
        }
    }
}
