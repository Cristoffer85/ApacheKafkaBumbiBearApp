package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import KafkaGroup.BumbiBearApp.payload.MySQLUser;
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

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup2")
    public void consume(MySQLUser mySQLUser) {
        LOGGER.info(String.format("Json message received -> %s", mySQLUser.toString()));

        // Skapa en instans av MongoUser och spara till Mongo-DB
        MongoUser mongoUser = new MongoUser();
        mongoUser.setSpecies(mySQLUser.getSpecies());
        mongoUser.setType(mySQLUser.getType());
        mongoUser.setFullname(mySQLUser.getFullname());

        mongoUserRepository.save(mongoUser);

        LOGGER.info("Message saved to MongoDB.");
    }
}



