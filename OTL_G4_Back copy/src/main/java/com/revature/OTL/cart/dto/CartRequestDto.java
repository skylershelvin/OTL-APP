package com.revature.OTL.cart.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequestDto {

    private int orderId;
    private LocalDateTime orderDate;
    private double totalPrice;
    private LocalDateTime EstDel;
    private int userId;
    private int carId;
    private String description;
}
