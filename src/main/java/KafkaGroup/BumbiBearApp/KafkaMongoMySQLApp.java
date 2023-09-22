package KafkaGroup.BumbiBearApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaMongoMySQLApp {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMongoMySQLApp.class, args);

        // NOTE: IF run/saving to either MongoDB server, or MySQL local storage isnt working, have you remembered to change the
        // MongoDB password in the class MongoDBConfig? and
        // MySQL password in the file application.properties?

        // ALSO!! REMOVE BEFORE PUSH!!
        // REMEMBER TO REMOVE, OR NOT TO PUSH THE TYPED IN PASSWORDS TO GITHUB!!

    }
}
