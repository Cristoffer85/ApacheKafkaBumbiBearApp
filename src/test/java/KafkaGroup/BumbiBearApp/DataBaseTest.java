package KafkaGroup.BumbiBearApp;

import KafkaGroup.BumbiBearApp.repository.MySQLUserRepository;
import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataBaseTest {


    @Autowired
    MySQLUserRepository mySqlUserRepository;
    static MySQLUser testMySQLUser;

    @BeforeEach
    void setUp() {System.out.println("Before test");}

    @AfterEach
    void tearDown() {System.out.println("After test");}

    @AfterAll
    static void afterAll() {
        System.out.println("All tests completed");
    }

    /*@Test
    @Order(1)
    void createUser() {
        // Skapa ett objekt av MySQLUser med specifik data
        MySQLUser user = new MySQLUser();
        user.setType("A");
        user.setFullname("B");

        // Spara MySQLUser till Db
        testMySQLUser = userRepository.save(user);

        assertNotNull(userRepository.findById(testMySQLUser.getId()).get().getType());
    }

    @Test
    @Order(2)
    void updateUser() {
        // Hämta en user
        MySQLUser fetchedUser = userRepository.findById(testMySQLUser.getId()).get();
        assertNotNull(fetchedUser);

        // Spara MySQLUser till Db
        fetchedUser.setType("Kalle");
        userRepository.save(fetchedUser);
        assertEquals("Kalle", userRepository.findById(testMySQLUser.getId()).get().getType());
    }
    @Test
    @Order(3)
    void removeUser(){
        assertNotNull(userRepository.findById(testMySQLUser.getId()).get());

        userRepository.deleteById(testMySQLUser.getId());
        assertTrue(userRepository.findById(testMySQLUser.getId()).isEmpty());
    }*/
}
