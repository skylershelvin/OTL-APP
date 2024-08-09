package com.revature.OTL.car;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.OTL.cart.Cart;
import com.revature.OTL.enums.Condition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String make;
    private String colour;
    private String model;
    private int year;
    private String trim;
    private double price;
    private String description;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cart> carts;
}
