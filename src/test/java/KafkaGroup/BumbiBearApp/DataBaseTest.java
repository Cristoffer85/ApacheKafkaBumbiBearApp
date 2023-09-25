package KafkaGroup.BumbiBearApp;

import KafkaGroup.BumbiBearApp.mysqlrepository.UserSqlRepository;
import KafkaGroup.BumbiBearApp.payload.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataBaseTest {


    @Autowired
    UserSqlRepository userSqlRepository;
    static User testUser;

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
        // Skapa ett objekt av User med specifik data
        User user = new User();
        user.setType("A");
        user.setFullname("B");

        // Spara User till Db
        testUser = userRepository.save(user);

        assertNotNull(userRepository.findById(testUser.getId()).get().getType());
    }

    @Test
    @Order(2)
    void updateUser() {
        // Hämta en user
        User fetchedUser = userRepository.findById(testUser.getId()).get();
        assertNotNull(fetchedUser);

        // Spara User till Db
        fetchedUser.setType("Kalle");
        userRepository.save(fetchedUser);
        assertEquals("Kalle", userRepository.findById(testUser.getId()).get().getType());
    }
    @Test
    @Order(3)
    void removeUser(){
        assertNotNull(userRepository.findById(testUser.getId()).get());

        userRepository.deleteById(testUser.getId());
        assertTrue(userRepository.findById(testUser.getId()).isEmpty());
    }*/
}
