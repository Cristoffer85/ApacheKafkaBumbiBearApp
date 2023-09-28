package KafkaGroup.BumbiBearApp;

import KafkaGroup.BumbiBearApp.ui.ConsoleUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "KafkaGroup.BumbiBearApp.repository")
public class MainBumbi {

    public static void main(String[] args) {
        SpringApplication.run(MainBumbi.class, args);

        ConsoleUI.runConsoleUI();
    }
}

        // NOTE: IF run/saving to either MongoDB server or MySQL local storage isnt working, have you remembered to change the
        // MongoDB and MySQL settings in the file application.properties?


        // ------> ALSO!! VERY BIG REMEMBER NOTICE!! <------

        // HAVE YOU REMEMBERED TO REMOVE, OR NOT TO PUSH TYPED IN PASSWORDS TO THE PUBLIC REPO ON GITHUB???

