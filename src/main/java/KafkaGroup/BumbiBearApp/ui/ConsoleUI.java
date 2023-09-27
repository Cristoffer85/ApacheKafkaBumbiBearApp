package KafkaGroup.BumbiBearApp.ui;

import KafkaGroup.BumbiBearApp.payload.MongoUser;
import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleUI {

    private static KafkaTemplate<String, MongoUser> mongoUserKafkaTemplate;
    private static KafkaTemplate<String, MySQLUser> mySQLUserKafkaTemplate;
    private static KafkaTemplate<String, String> txtKafkaTemplate;

    @Autowired
    public ConsoleUI(KafkaTemplate<String, MongoUser> mongoUserKafkaTemplate,
                     KafkaTemplate<String, MySQLUser> mySQLUserKafkaTemplate) {
        ConsoleUI.mongoUserKafkaTemplate = mongoUserKafkaTemplate;
        ConsoleUI.mySQLUserKafkaTemplate = mySQLUserKafkaTemplate;
    }

    @Autowired
    public void setTxtKafkaTemplate(KafkaTemplate<String, String> txtKafkaTemplate) {
        ConsoleUI.txtKafkaTemplate = txtKafkaTemplate;
    }

    public static void runConsoleUI() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> sendToAllConsumers(scanner);
                case 2 -> {}// Option 2 logiken
                case 0 -> {System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("===== Console UI Menu =====");
        System.out.println("1. Send Data to Kafka (All Consumers)");
        System.out.println("2. Option 2 (Dormant, right now)");
        System.out.println("0. Exit");
        System.out.println("===========================");
        System.out.print("Enter your choice: \n");
    }

    public static void sendToAllConsumers(Scanner scanner) {
        System.out.print("Enter species: ");
        String species = scanner.nextLine();

        System.out.print("Enter type: ");
        String type = scanner.nextLine();

        System.out.print("Enter fullname: ");
        String fullname = scanner.nextLine();

        // Create MongoUser object
        MongoUser mongoUser = new MongoUser();
        mongoUser.setSpecies(species);
        mongoUser.setType(type);
        mongoUser.setFullname(fullname);

        // Create MySQLUser object
        MySQLUser mySQLUser = new MySQLUser();
        mySQLUser.setSpecies(species);
        mySQLUser.setType(type);
        mySQLUser.setFullname(fullname);

        // Send data to MongoDB and MySQL consumers
        mongoUserKafkaTemplate.send("mongodb_data", mongoUser);
        mySQLUserKafkaTemplate.send("mysql_data", mySQLUser);

        // Send data to TxtConsumer (Uses MongoUser class, since it was easy applicable. No need to create a third User/Entity class just for that, i thought.)
        txtKafkaTemplate.send("javaguides_json", mongoUser.toString());

        System.out.println("Data sent to MongoDB- MySQLDB- and TxtConsumer.");
    }
}


