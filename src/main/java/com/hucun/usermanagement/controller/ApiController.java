/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hucun.usermanagement.controller;


import com.hucun.usermanagement.model.User;
import com.hucun.usermanagement.repo.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserRepo userRepo;
    
    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome to hucun's RESTful API for managing data of users";
    }
    
    
    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    
    @PostMapping(value ="/users/add")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "Saved...";
    }
    
    @PutMapping(value = "/users/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
       User updateUser = userRepo.findById(id).get();
       updateUser.setFirstName(user.getFirstName());
       updateUser.setLastName(user.getLastName());
       updateUser.setAge(user.getAge());
       updateUser.setOccupation(user.getOccupation());
       userRepo.save(updateUser);
       return "Updated..";
    }
    
    @DeleteMapping(value = "/users/delete/{id}") 
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "Delete user with id: " + id ;
    }
}
