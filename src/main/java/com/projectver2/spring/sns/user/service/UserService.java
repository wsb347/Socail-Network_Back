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
            // 기존 정보와 새로운 정보를 비교하여 업데이트
            existingUser.setNickname(user.getUsername());
            // 기타 필요한 정보 업데이트
            return userRepository.save(existingUser);
        })
        .orElseGet(() -> {
            // 새로운 사용자 저장
            return userRepository.save(user);
        });
}
    public Optional<User> findByUserid(String userid) {
        return userRepository.findByUserid(userid);
    }
}
