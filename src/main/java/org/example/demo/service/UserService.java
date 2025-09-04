package org.example.demo.service;

import org.example.demo.entitys.UserModel;
import org.example.demo.repository.UserRepository;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void registerUser(UserModel user) {
        userRepository.save(user);
    }

    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserModel findById(String id) {
        return userRepository.findById(id);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(UserModel user) {
        userRepository.update(user);
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    public void close() {
        userRepository.close();
    }
}
