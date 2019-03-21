package project.areas.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.areas.users.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    User findUserByEmail(String email);
}
