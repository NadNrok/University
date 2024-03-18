package com.fm.University.service;

import com.fm.University.model.AppUser;
import com.fm.University.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    
    private final AppUserRepository userRepository;
    
    @Autowired
    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }
    
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<AppUser> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    
    public AppUser updateUser(Long userId, AppUser userDetails) {
        AppUser user = userRepository.findById(userId)
                                     .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setRoles(userDetails.getRoles());
        return userRepository.save(user);
    }
    
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
