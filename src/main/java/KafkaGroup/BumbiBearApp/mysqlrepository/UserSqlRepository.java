package KafkaGroup.BumbiBearApp.mysqlrepository;

import KafkaGroup.BumbiBearApp.payload.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSqlRepository extends JpaRepository<User, Long> {
    // Define custom queries or methods specific to MySQL, if needed
}
