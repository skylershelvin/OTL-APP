package com.revature.OTL.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.OTL.cart.Cart;

import com.revature.OTL.user.dto.UserRequestDTO;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cart> carts;

    public static AppUser fromDTO(UserRequestDTO dto) {
        return AppUser.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
