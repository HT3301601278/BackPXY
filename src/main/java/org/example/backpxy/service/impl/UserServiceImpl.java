package org.example.backpxy.service.impl;

import org.example.backpxy.model.User;
import org.example.backpxy.repository.UserRepository;
import org.example.backpxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("该电子邮件地址已被注册");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("该用户名已被注册");
        }
        return userRepository.save(user);
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        if (!oldPassword.equals(user.getPassword())) {
            throw new RuntimeException("旧密码不正确");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setAvatarUrl(user.getAvatarUrl());

        return userRepository.save(existingUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("用户名不存在"));
        
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("密码不正确");
        }
        
        return user;
    }
}