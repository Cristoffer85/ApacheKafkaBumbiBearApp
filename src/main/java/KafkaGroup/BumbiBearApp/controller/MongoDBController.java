package KafkaGroup.BumbiBearApp.controller;

import KafkaGroup.BumbiBearApp.payload.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoDBController {
    private final MongoTemplate localMongoTemplate;
    private final MongoTemplate remoteMongoTemplate;

    @Autowired
    public MongoDBController(@Qualifier("localMongoTemplate") MongoTemplate localMongoTemplate,
                             @Qualifier("remoteMongoTemplate") MongoTemplate remoteMongoTemplate) {
        this.localMongoTemplate = localMongoTemplate;
        this.remoteMongoTemplate = remoteMongoTemplate;
    }

    public void saveData(User user) {
        try {
            // Attempt to save to the remote MongoDB server
            remoteMongoTemplate.save(user);
        } catch (Exception e) {
            // If the remote save fails, fall back to the local MongoDB server
            localMongoTemplate.save(user);
        }
    }
}
