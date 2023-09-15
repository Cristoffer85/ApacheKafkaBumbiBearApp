package mygroup.kafkaspringbootlektionmarcus.repository;

import mygroup.kafkaspringbootlektionmarcus.payload.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
