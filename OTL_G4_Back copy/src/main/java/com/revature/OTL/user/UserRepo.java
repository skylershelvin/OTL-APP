package com.revature.OTL.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Integer> {

     Optional<AppUser> findByUsername(String username);
     Optional<AppUser> findByUsernameAndPassword(String username, String password);
}
