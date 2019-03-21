package project.areas.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.areas.users.entities.Role;

import java.util.Set;

public interface RoleRepository  extends JpaRepository<Role,Integer>{
    Role getRoleByName(String roleName);
}
