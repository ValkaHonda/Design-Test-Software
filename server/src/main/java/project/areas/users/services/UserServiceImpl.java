package project.areas.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.areas.users.entities.Role;
import project.areas.users.entities.User;
import project.areas.users.models.bidingModels.UserRegisterForm;
import project.areas.users.repositories.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(final BCryptPasswordEncoder bCryptPasswordEncoder,
                           final UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(final UserRegisterForm user,final Role role) {
        user.setPass(this.bCryptPasswordEncoder.encode(user.getPass()));
        User userEntity = new User(user.getEmail(),user.getPass());
        userEntity.setRoles(new HashSet<>());
        userEntity.getRoles().add(role);
        this.userRepository.saveAndFlush(userEntity);
    }
}
