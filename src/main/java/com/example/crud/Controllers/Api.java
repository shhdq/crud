package com.example.crud.Controllers;

import com.example.crud.Models.User;
import com.example.crud.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Api {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "<h1>Home page</h1>";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        userRepo.save(user);
        return "User created";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).get();

        userRepo.delete(deleteUser);

        return "User deleted";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updateUser = userRepo.findById(id).get();

        updateUser.setUsername(user.getUsername());
        userRepo.save(updateUser);

        return "User updated successfully";

    }
}
