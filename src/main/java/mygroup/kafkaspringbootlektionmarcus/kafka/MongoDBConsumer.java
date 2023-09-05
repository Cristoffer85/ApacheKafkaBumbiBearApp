package mygroup.kafkaspringbootlektionmarcus.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import mygroup.kafkaspringbootlektionmarcus.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MongoDBConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBConsumer.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDBConsumer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup2")
    public void consume(User user) {
        LOGGER.info(String.format("Json message received -> %s", user.toString()));

        // Save the message to MongoDB
        saveMessageToMongoDB(user);
    }

    private void saveMessageToMongoDB(User user) {
        // Use the MongoTemplate to interact with MongoDB
        mongoTemplate.save(user, "MongoDBLocalCollection");

        LOGGER.info("Message saved to MongoDB");
    }
}
