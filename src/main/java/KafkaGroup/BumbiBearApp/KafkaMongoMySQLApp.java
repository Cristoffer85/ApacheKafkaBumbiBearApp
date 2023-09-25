package KafkaGroup.BumbiBearApp;

import KafkaGroup.BumbiBearApp.ui.ConsoleUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "KafkaGroup.BumbiBearApp.mysqlrepository")
@EnableMongoRepositories(basePackages = "KafkaGroup.BumbiBearApp.mongorepository")
public class KafkaMongoMySQLApp {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMongoMySQLApp.class, args);

        ConsoleUI.runConsoleUI();
    }
}

        // NOTE: IF run/saving to either MongoDB server, or MySQL local storage isnt working, have you remembered to change the
        // MongoDB password in the class MongoDBConfig? and
        // MySQL password in the file application.properties?

        // ALSO!! REMOVE BEFORE PUSH!!
        // REMEMBER TO REMOVE, OR NOT TO PUSH THE TYPED IN PASSWORDS TO GITHUB!!

