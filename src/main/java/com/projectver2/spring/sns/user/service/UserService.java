package com.projectver2.spring.sns.user.service;

import com.projectver2.spring.sns.user.domain.User;
import com.projectver2.spring.sns.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveOrUpdate(User user) {
    return userRepository.findByEmail(user.getEmail())
        .map(existingUser -> {
            existingUser.setNickname(user.getUsername());
            return userRepository.save(existingUser);
        })
        .orElseGet(() -> {
            return userRepository.save(user);
        });
}
    public Optional<User> findByUserid(String userid) {
        return userRepository.findByUserid(userid);
    }
}
