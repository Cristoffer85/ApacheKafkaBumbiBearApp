package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class TxtConsumer {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(TxtConsumer.class);
    @KafkaListener(topics = "javaguides_json", groupId = "myGroup1")
    public void consume(MySQLUser mySQLUser) {
        LOGGER.info(String.format("Json message recieved -> %s", mySQLUser.toString()));

        // Spara datan till lokal .txt-fil
        saveMessageToFile(mySQLUser.toString());
    }

    private void saveMessageToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rec_messages_myGroup1.txt", true))) {
            writer.println(message);
        } catch (IOException e) {
            LOGGER.error("Error saving message to file", e);
        }
    }
}
