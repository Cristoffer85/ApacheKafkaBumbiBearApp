package KafkaGroup.BumbiBearApp.fetch;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/mongo-users")
public class MongoUserController {

    private final MyMongoService mongoService;

    @Autowired
    public MongoUserController(MyMongoService mongoService) {
        this.mongoService = mongoService;
    }

    @GetMapping
    public List<MongoUser> getAllMongoUsers() {
        return mongoService.getAllMongoUsers();
    }
}
