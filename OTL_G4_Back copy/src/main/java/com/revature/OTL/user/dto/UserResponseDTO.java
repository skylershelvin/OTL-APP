package com.revature.OTL.user.dto;

import com.revature.OTL.user.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
     private String username;
     private String password;
     private String email;
}
