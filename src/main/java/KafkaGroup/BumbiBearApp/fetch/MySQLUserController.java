package KafkaGroup.BumbiBearApp.fetch;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/mysql-users")
public class MySQLUserController {

    private final MySQLService mySQLService;

    @Autowired
    public MySQLUserController(MySQLService mySQLService) {
        this.mySQLService = mySQLService;
    }

    @GetMapping
    public List<MySQLUser> getAllMySQLUsers() {
        return mySQLService.getAllMySQLUsers();
    }
}

