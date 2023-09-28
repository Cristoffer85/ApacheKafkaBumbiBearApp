package KafkaGroup.BumbiBearApp;

import KafkaGroup.BumbiBearApp.repository.MySQLUserRepository;
import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataBaseTest {

    @Autowired
    MySQLUserRepository mySqlUserRepository; // Fix the field name here
    static MySQLUser testMySQLUser;

    @BeforeEach
    void setUp() { System.out.println("Before test"); }

    @AfterEach
    void tearDown() { System.out.println("After test"); }

    @AfterAll
    static void afterAll() {
        System.out.println("All tests completed");
    }

    @Test
    @Order(1)
    void createUser() {
        // Skapa ett objekt av MySQLUser med specifik data
        MySQLUser user = new MySQLUser();
        user.setType("A");
        user.setFullname("B");

        // Spara MySQLUser till Db
        testMySQLUser = mySqlUserRepository.save(user); // Use mySqlUserRepository here

        assertNotNull(mySqlUserRepository.findById(testMySQLUser.getId()).get().getType()); // Use mySqlUserRepository here
    }

    @Test
    @Order(2)
    void updateUser() {
        // Hämta en user
        MySQLUser fetchedUser = mySqlUserRepository.findById(testMySQLUser.getId()).get(); // Use mySqlUserRepository here
        assertNotNull(fetchedUser);

        // Spara MySQLUser till Db
        fetchedUser.setType("Kalle");
        mySqlUserRepository.save(fetchedUser); // Use mySqlUserRepository here
        assertEquals("Kalle", mySqlUserRepository.findById(testMySQLUser.getId()).get().getType()); // Use mySqlUserRepository here
    }

    @Test
    @Order(3)
    void removeUser() {
        assertNotNull(mySqlUserRepository.findById(testMySQLUser.getId()).get()); // Use mySqlUserRepository here

        mySqlUserRepository.deleteById(testMySQLUser.getId()); // Use mySqlUserRepository here
        assertTrue(mySqlUserRepository.findById(testMySQLUser.getId()).isEmpty()); // Use mySqlUserRepository here
    }
}

