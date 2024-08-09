package com.revature.OTL.cart;

import java.util.List;
import java.util.Optional;

import com.revature.OTL.cart.dto.CartRequestDto;

;

public interface CartService {

    List<CartRequestDto> getAllCarts();

    Optional<CartRequestDto> getCartById(int id);

    CartRequestDto createCart(CartRequestDto cartDTO);

    Cart updateCart(int id, CartRequestDto cartDTO);

    void deleteCart(int id);
}
