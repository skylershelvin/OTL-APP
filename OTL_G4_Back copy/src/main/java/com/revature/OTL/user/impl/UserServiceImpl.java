package com.revature.OTL.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.OTL.user.AppUser;
import com.revature.OTL.user.UserRepo;
import com.revature.OTL.user.UserService;
import com.revature.OTL.user.dto.UserRequestDTO;
import com.revature.OTL.util.exceptions.DataNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean login(String username, String password) {
        Optional<AppUser> userOptional = userRepo.findByUsernameAndPassword(username, password);
        return userOptional.isPresent();
    }

    @Override
    public Optional<AppUser> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public AppUser registerUser(UserRequestDTO userRequestDTO) {
        AppUser user = AppUser.fromDTO(userRequestDTO);
        return userRepo.save(user);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public AppUser getUserById(int id) {
        AppUser appUser = userRepo.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
        return appUser;
    }

    @Override
    public AppUser updateUser(int id, UserRequestDTO userRequestDTO) {
        AppUser appUser = userRepo.findById(id).orElseThrow(()->new DataNotFoundException("User not found " +id));
        appUser.setEmail(userRequestDTO.getEmail());
        appUser.setUsername(userRequestDTO.getUsername());
        appUser.setPassword(userRequestDTO.getPassword());
        return userRepo.save(appUser);
    }

    @Override
    public void deleteUser(int id) {

    }
}
