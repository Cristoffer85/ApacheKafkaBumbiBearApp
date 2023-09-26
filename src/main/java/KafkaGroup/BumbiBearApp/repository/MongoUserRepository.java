package KafkaGroup.BumbiBearApp.repository;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {
}
