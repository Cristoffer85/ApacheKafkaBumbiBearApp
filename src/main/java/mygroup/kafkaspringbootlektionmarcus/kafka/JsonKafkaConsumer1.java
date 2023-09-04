package mygroup.kafkaspringbootlektionmarcus.kafka;

import mygroup.kafkaspringbootlektionmarcus.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class JsonKafkaConsumer1 {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(JsonKafkaConsumer1.class);
    @KafkaListener(topics = "javaguides_json", groupId = "myGroup1")
    public void consume(User user) {
        LOGGER.info(String.format("Json message recieved -> %s", user.toString()));

        // Save the message to an external .txt file
        saveMessageToFile(user.toString());
    }

    private void saveMessageToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rec_messages_myGroup1.txt", true))) {
            writer.println(message);
        } catch (IOException e) {
            LOGGER.error("Error saving message to file", e);
        }
    }
}
