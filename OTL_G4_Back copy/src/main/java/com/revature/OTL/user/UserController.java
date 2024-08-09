package com.revature.OTL.user;

import java.util.List;

import com.revature.OTL.user.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
     private final UserService userService;

     @Autowired
     public UserController(UserService userService) {
          this.userService = userService;
     }

     /**
      * g
      * @param newAppUser
      * @return ResponseEntity object, response status, and response body
      */
     @PostMapping("/register")
     private ResponseEntity<AppUser> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
          return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDTO));
     }

     @PostMapping("/login")
     public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
          boolean isLoggedIn = userService.login(username, password);
          if (isLoggedIn) {
               // Successful login, return appropriate response
               return ResponseEntity.ok("Login successful");
          } else {
               // Handle failed login, e.g., return error response
               return ResponseEntity.status(401).body("Invalid credentials");
          }
     }
     @GetMapping("/{id}")
     public ResponseEntity<AppUser> findUserById(@PathVariable int id){
         return ResponseEntity.ok(userService.getUserById(id));
     }

     @GetMapping
     public ResponseEntity<List<AppUser>> findAll(){
          return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getAllUsers());
     }

     @PutMapping("/{id}")
     public ResponseEntity<AppUser> updateUser(@PathVariable int id, @RequestBody UserRequestDTO userRequestDTO){
          return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(userService.updateUser(id,userRequestDTO));
     }
}
