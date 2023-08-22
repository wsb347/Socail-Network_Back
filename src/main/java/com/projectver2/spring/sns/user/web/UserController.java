package com.projectver2.spring.sns.user.web;

import com.projectver2.spring.sns.user.domain.User;
import com.projectver2.spring.sns.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/{userid}")
    public Optional<User> findByUserid(@PathVariable String userid){

        return userService.findByUserid(userid);
    }

}


