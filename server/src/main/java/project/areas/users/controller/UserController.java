package project.areas.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.areas.users.entities.Role;
import project.areas.users.models.bidingModels.UserRegisterForm;
import project.areas.users.services.RoleService;
import project.areas.users.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(final UserService userService, final RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/test")
    public String test(){
        return "<h1>Hello from UserController.<h1>";
    }
    @PostMapping("/sign-up")
    public ResponseEntity registerUser(@RequestBody final UserRegisterForm registerForm){
        Role role = this.roleService.getRoleByName("ROLE_NORMAL");
        this.userService.registerUser(registerForm,role);
        return ResponseEntity.ok("Successful sign-up");
    }
}
