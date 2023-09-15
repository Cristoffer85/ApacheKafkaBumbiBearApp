package mygroup.kafkaspringbootlektionmarcus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSpringbootLektionMarcusApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringbootLektionMarcusApplication.class, args);

        // NOTE: IF run/saving to either MongoDB server, or MySQL local storage isnt working, have you remembered to change the
        // MongoDB password in the class MongoDBConfig? and
        // MySQL password in the file application.properties?

    }
}
