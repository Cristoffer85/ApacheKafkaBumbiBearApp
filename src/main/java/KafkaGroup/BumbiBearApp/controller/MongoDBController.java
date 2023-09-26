package KafkaGroup.BumbiBearApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import KafkaGroup.BumbiBearApp.payload.MongoUser;

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

    public void saveData(MongoUser mongoUser) {
        try {
            remoteMongoTemplate.save(mongoUser);
        } catch (Exception e) {
            localMongoTemplate.save(mongoUser);
        }
    }
}

