package KafkaGroup.BumbiBearApp.consumer;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import KafkaGroup.BumbiBearApp.repository.MySqlUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MySQLDBConsumer {
    private final MySqlUserRepository mySQLUserRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDBConsumer.class);

    @Autowired
    public MySQLDBConsumer(MySqlUserRepository mySQLUserRepository) {
        this.mySQLUserRepository = mySQLUserRepository;
    }

    @KafkaListener(topics = "mysql_data", groupId = "myMySQLGroup")
    public void consume(MySQLUser mySQLUser) {
        LOGGER.info(String.format("MySQL message received -> %s", mySQLUser.toString()));

        try {
            mySQLUserRepository.save(mySQLUser);
            LOGGER.info("Message saved to MySQL.");
        } catch (Exception e) {
            LOGGER.error("Error saving message to MySQL", e);
        }
    }
}


