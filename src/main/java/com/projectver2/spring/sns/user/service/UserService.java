package com.projectver2.spring.sns.user.service;

import com.projectver2.spring.sns.user.domain.User;
import com.projectver2.spring.sns.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                    existingUser.setUsername(user.getUsername());
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    return userRepository.save(user);
                });
    }

    public Optional<User> findByUserid(String userid) {
        return userRepository.findByUserid(userid);
    }

    public User userModify(String userid, User user) {
        return userRepository.findByUserid(userid)
                .map(existingUser -> {
                    if (existingUser.getUsername() != null) existingUser.setUsername(user.getUsername());
                    if (existingUser.getEmail() != null) existingUser.setEmail(user.getEmail());
                    if (existingUser.getCity() != null) existingUser.setCity(user.getCity());
                    if (existingUser.getPassword() != null) existingUser.setPassword(user.getPassword());

                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    return userRepository.save(user);
                });


    }

    @Transactional
    public User userDelete(String userid) {

        userRepository.deleteUserByUserid(userid);

        return null;
    }
}
