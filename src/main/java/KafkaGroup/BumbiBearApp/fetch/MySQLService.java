package KafkaGroup.BumbiBearApp.fetch;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import KafkaGroup.BumbiBearApp.repository.MySQLUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MySQLService {

    private final MySQLUserRepository mySQLUserRepository;

    @Autowired
    public MySQLService(MySQLUserRepository mySQLUserRepository) {
        this.mySQLUserRepository = mySQLUserRepository;
    }

    public List<MySQLUser> getAllMySQLUsers() {
        return mySQLUserRepository.findAll();
    }
}

