package KafkaGroup.BumbiBearApp.ui;

import KafkaGroup.BumbiBearApp.payload.User;
import KafkaGroup.BumbiBearApp.producer.JsonKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Scanner;

    @Component
    public class ConsoleUI {

        private final KafkaTemplate<String, String> stringKafkaTemplate;
        private final KafkaTemplate<String, User> userKafkaTemplate;

        @Autowired
        public ConsoleUI(KafkaTemplate<String, String> stringKafkaTemplate,
                         KafkaTemplate<String, User> userKafkaTemplate) {
            this.stringKafkaTemplate = stringKafkaTemplate;
            this.userKafkaTemplate = userKafkaTemplate;
        }

        public static void runConsoleUI() {
            // Initialize any other dependencies here, if needed

            Scanner scanner = new Scanner(System.in);

            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        sendUserDataToKafka(scanner);
                        break;
                    case 2:
                        // Add more options as needed
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        public static void displayMenu() {
            System.out.println("===== Console UI Menu =====");
            System.out.println("1. Send User Data to Kafka");
            System.out.println("2. Option 2");
            // Add more menu options as needed
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
        }

        public static void sendUserDataToKafka(Scanner scanner) {
            System.out.print("Enter species: ");
            String species = scanner.nextLine();

            System.out.print("Enter type: ");
            String type = scanner.nextLine();

            System.out.print("Enter fullname: ");
            String fullname = scanner.nextLine();

            // Now, you can create a User object and send it to your Kafka producer
            User user = new User();
            user.setSpecies(species);
            user.setType(type);
            user.setFullname(fullname);

            // Call the Kafka producer to send the user data
            // You may need to obtain the Kafka producer instance or use Spring's dependency injection
            // For simplicity, I'm assuming a static method here
            JsonKafkaProducer.sendMessage(user);

            System.out.println("User data sent to Kafka.");
        }
    }
