package KafkaGroup.BumbiBearApp.ui;

import KafkaGroup.BumbiBearApp.fetch.MySQLService;
import KafkaGroup.BumbiBearApp.payload.MongoUser;
import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import KafkaGroup.BumbiBearApp.fetch.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@Service
public class ConsoleUI {

    private static KafkaTemplate<String, MongoUser> mongoUserKafkaTemplate;
    private static KafkaTemplate<String, MySQLUser> mySQLUserKafkaTemplate;
    private static KafkaTemplate<String, String> txtKafkaTemplate;
    private static MongoService mongoService;
    private static MySQLService mySQLService;

    @Autowired
    public ConsoleUI(
            KafkaTemplate<String, MongoUser> mongoUserKafkaTemplate,
            KafkaTemplate<String, MySQLUser> mySQLUserKafkaTemplate,
            KafkaTemplate<String, String> txtKafkaTemplate,
            MongoService mongoService,
            MySQLService mySQLService
    ) {
        ConsoleUI.mongoUserKafkaTemplate = mongoUserKafkaTemplate;
        ConsoleUI.mySQLUserKafkaTemplate = mySQLUserKafkaTemplate;
        ConsoleUI.txtKafkaTemplate = txtKafkaTemplate;
        ConsoleUI.mongoService = mongoService;
        ConsoleUI.mySQLService = mySQLService;
    }

    public static void runConsoleUI() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> sendToAllConsumers(scanner);
                case 2 -> displayDataFromDatabases();
                case 0 -> {System.out.println("Exiting..."); return; }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("===== Console UI Menu =====");
        System.out.println("1. Send Data (through Kafka) to MongoDB, MySQL & .Txt");
        System.out.println("2. View Data from MongoDB, MySQL & .Txt");
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

        // Fault-handling to not accept a blank input to be typed into the database
        if (species.isEmpty() || type.isEmpty() || fullname.isEmpty()) {
            System.out.println("Error: Input cannot be blank.");
            return;
        }

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

        // Send data to TxtConsumer (Uses MongoUser class, since it was easily applicable. No need to create a third User/Entity class just for that.)
        txtKafkaTemplate.send("BumbiRegister", mongoUser.toString());

        System.out.println("Data sent to MongoDB- MySQLDB- and TxtConsumer.");
    }


    public static void displayDataFromDatabases() {
        // Display/List MongoDB data
        List<MongoUser> mongoUsers = mongoService.getAllMongoUsers();
        System.out.println("MongoDB Data:");
        for (MongoUser user : mongoUsers) {
            System.out.println(user.toString());
        }

        // Display/List MySQL data
        List<MySQLUser> mySQLUsers = mySQLService.getAllMySQLUsers();
        System.out.println("\nMySQL Data:");
        for (MySQLUser user : mySQLUsers) {
            System.out.println(user.toString());
        }

        // Display/List .Txt data
        // (Had to add/have the try-catch here as well, since the TxtConsumer have it, dont know why by i couldnt make it work without it. Even since both MongoDB and MySQLConsumer have try-catches as well. Wellwell, question for another decade, i suppose.
        System.out.println("\n.Txt File Data:");
        try {
            List<String> lines = Files.readAllLines(Paths.get("rec_messages_myGroup1.txt"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading .txt file: " + e.getMessage());
        }
    }
}


