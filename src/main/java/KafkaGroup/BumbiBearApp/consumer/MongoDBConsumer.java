package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import KafkaGroup.BumbiBearApp.repository.MongoUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MongoDBConsumer {
    private final MongoUserRepository mongoUserRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBConsumer.class);

    @Autowired
    public MongoDBConsumer(MongoUserRepository mongoUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
    }

    @KafkaListener(topics = "mongodb_data", groupId = "myMongoGroup")
    public void consume(MongoUser mongoUser) {
        LOGGER.info(String.format("MongoDB message received -> %s", mongoUser.toString()));

        try {
            mongoUserRepository.save(mongoUser);
            LOGGER.info("Message saved to MongoDB.");
        } catch (Exception e) {
            LOGGER.error("Error saving message to MongoDB", e);
        }
    }
}