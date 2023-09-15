package mygroup.kafkaspringbootlektionmarcus.kafka;

import mygroup.kafkaspringbootlektionmarcus.payload.User;
import mygroup.kafkaspringbootlektionmarcus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumerMySQLDb {

    @Autowired //Autowired används för att kunna använda något i samband med webapplikation
    private UserRepository userRepository;

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup3")
    public void writeToDb(User user){

        // Skicka datan till Db
        userRepository.save(user);
    }
}
