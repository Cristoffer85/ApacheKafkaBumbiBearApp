package KafkaGroup.BumbiBearApp.ui;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import KafkaGroup.BumbiBearApp.producer.JsonKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Scanner;

    @Component
    public class ConsoleUI {

        private final KafkaTemplate<String, String> stringKafkaTemplate;
        private final KafkaTemplate<String, MySQLUser> userKafkaTemplate;

        @Autowired
        public ConsoleUI(KafkaTemplate<String, String> stringKafkaTemplate,
                         KafkaTemplate<String, MySQLUser> userKafkaTemplate) {
            this.stringKafkaTemplate = stringKafkaTemplate;
            this.userKafkaTemplate = userKafkaTemplate;
        }

        public static void runConsoleUI() {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        sendUserDataToKafka(scanner);
                        break;
                    case 2:
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
            System.out.println("1. Send MySQLUser Data to Kafka");
            System.out.println("2. Option 2 (Dormant, right now atleast..)");
            System.out.println("0. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");
        }

        public static void sendUserDataToKafka(Scanner scanner) {
            System.out.print("Enter species: ");
            String species = scanner.nextLine();

            System.out.print("Enter type: ");
            String type = scanner.nextLine();

            System.out.print("Enter fullname: ");
            String fullname = scanner.nextLine();


            // Move this to another class? Possible? Since in MongoDBConsumer, its located there, check up, move refactor?
            MySQLUser mySQLUser = new MySQLUser();
            mySQLUser.setSpecies(species);
            mySQLUser.setType(type);
            mySQLUser.setFullname(fullname);
            //----------------------------------------------

            JsonKafkaProducer.sendMessage(mySQLUser);

            System.out.println("MySQLUser data sent to Kafka.");
        }
    }
