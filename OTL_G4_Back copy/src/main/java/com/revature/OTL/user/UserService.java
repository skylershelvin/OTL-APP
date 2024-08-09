package com.revature.OTL.user;


import com.revature.OTL.user.dto.UserRequestDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
     boolean login(String username, String password);
     Optional<AppUser> getUserByUsername(String username);
     AppUser registerUser(UserRequestDTO userRequestDTO);
     List<AppUser> getAllUsers();
     AppUser getUserById(int id);
     AppUser updateUser(int id, UserRequestDTO userRequestDTO);
     void deleteUser(int id);

}
