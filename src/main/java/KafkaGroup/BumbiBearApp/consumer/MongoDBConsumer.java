package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.controller.MongoDBController;
import KafkaGroup.BumbiBearApp.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MongoDBConsumer {
    private final MongoDBController mongoDBController;
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBConsumer.class);


    @Autowired
    public MongoDBConsumer(MongoDBController mongoDBController) {
        this.mongoDBController = mongoDBController;
    }

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup2")
    public void consume(User user) {
        LOGGER.info(String.format("Json message received -> %s", user.toString()));

        mongoDBController.saveData(user);

        LOGGER.info("Message saved to remote MongoDB Server.");
    }
}

