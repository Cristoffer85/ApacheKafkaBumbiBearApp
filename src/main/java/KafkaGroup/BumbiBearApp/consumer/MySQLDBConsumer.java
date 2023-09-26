package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import KafkaGroup.BumbiBearApp.repository.MySqlUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MySQLDBConsumer {

    @Autowired
    private MySqlUserRepository mySqlUserRepository;

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup3")
    public void writeToDb(MySQLUser mySQLUser){

        mySqlUserRepository.save(mySQLUser);
    }
}
