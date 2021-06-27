package com.spring.test.spb.controller;

import com.spring.test.spb.entities.User;
import com.spring.test.spb.repositories.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable( value = "id") long userId){
        User user = userRepository.getById(userId);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User postNewUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable(value = "id") long userId, @RequestBody User userPayload){
        User user = userRepository.getById(userId);

        user.setEmail(userPayload.getEmail());
        user.setLastName(userPayload.getLastName());
        user.setFirstName(userPayload.getFirstName());
        user.setUpdatedAt(new Date());
        final User updateUser = userRepository.save(user);

        return  ResponseEntity.ok().body(updateUser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> DeleteUser(@PathVariable(value = "id") long userId) throws Exception{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

         userRepository.delete(user);
         Map<String, Boolean> response = new HashMap<>();
         response.put("Deleted", Boolean.TRUE);
         return response;
    }

}
