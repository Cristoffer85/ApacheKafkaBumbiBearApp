package KafkaGroup.BumbiBearApp.fetch;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import KafkaGroup.BumbiBearApp.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMongoService {

    private final MongoUserRepository mongoUserRepository;

    @Autowired
    public MyMongoService(MongoUserRepository mongoUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
    }

    public List<MongoUser> getAllMongoUsers() {
        return mongoUserRepository.findAll();
    }
}
