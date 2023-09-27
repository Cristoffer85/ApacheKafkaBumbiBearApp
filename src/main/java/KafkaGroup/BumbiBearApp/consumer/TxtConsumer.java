package KafkaGroup.BumbiBearApp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class TxtConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TxtConsumer.class);

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup1")
    public void consume(String message) {
        LOGGER.info(String.format(".Txt message received -> %s", message));

        saveMessageToFile(message);

        LOGGER.info("Message saved to .Txt.");
    }

    private void saveMessageToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rec_messages_myGroup1.txt", true))) {
            writer.println(message);
        } catch (IOException e) {
            LOGGER.error("Error saving message to file", e);
        }
    }
}

