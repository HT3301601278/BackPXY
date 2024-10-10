package org.example.backpxy.service;

import org.example.backpxy.model.User;

public interface UserService {
    User registerUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User updateUser(User user);
    void changePassword(Long userId, String oldPassword, String newPassword);
}