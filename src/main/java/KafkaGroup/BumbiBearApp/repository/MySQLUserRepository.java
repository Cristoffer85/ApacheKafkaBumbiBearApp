package KafkaGroup.BumbiBearApp.repository;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySQLUserRepository extends JpaRepository<MySQLUser, Long> {
}
