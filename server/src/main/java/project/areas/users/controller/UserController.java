package project.areas.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.areas.users.models.bidingModels.UserRegisterForm;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    @GetMapping("/test")
    public String test(){
        return "<h1>Hello from UserController.<h1>";
    }
    @PostMapping("/sign-up")
    public ResponseEntity registerUser(@RequestBody final UserRegisterForm registerForm){
        System.out.println(registerForm.getEmail());
        System.out.println(registerForm.getPass());

        return ResponseEntity.ok("Successful sign-up");
    }
}
