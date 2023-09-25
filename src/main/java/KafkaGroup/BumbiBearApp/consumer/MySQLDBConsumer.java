package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.User;
import KafkaGroup.BumbiBearApp.mysqlrepository.UserSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MySQLDBConsumer {

    @Autowired //Autowired används för att kunna använda något i samband med webapplikation
    private UserSqlRepository userSqlRepository;

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup3")
    public void writeToDb(User user){

        // Skicka datan till Db
        userSqlRepository.save(user);
    }
}
