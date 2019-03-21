package project.areas.users.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "")
@RequestMapping("/users")
public class UserController {
    @GetMapping("/test")
    public String test(){
        return "<h1>Hello from UserController.<h1>";
    }
    @PostMapping("/sign-up")
    public String registerUser(){
        
    }
}
