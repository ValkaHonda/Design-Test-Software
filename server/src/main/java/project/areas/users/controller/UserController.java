package project.areas.users.controller;

import org.springframework.web.bind.annotation.*;
import project.areas.users.models.bidingModels.UserRegisterForm;

@RestController
@CrossOrigin(origins = "")
@RequestMapping("/users")
public class UserController {
    @GetMapping("/test")
    public String test(){
        return "<h1>Hello from UserController.<h1>";
    }
    @PostMapping("/sign-up")
    public String registerUser(@RequestBody final UserRegisterForm registerForm){
        System.out.println(registerForm.getEmail());
        System.out.println(registerForm.getEmail());
        System.out.println(registerForm.getEmail());
        System.out.println(registerForm.getEmail());
        System.out.println(registerForm.getEmail());
        System.out.println(registerForm.getEmail());

        return "<h1>Sucessful register<h1>";
    };
    }
}
