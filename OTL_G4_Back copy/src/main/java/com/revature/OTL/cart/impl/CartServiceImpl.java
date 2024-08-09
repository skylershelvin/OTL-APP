package com.revature.OTL.cart.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.OTL.car.CarRepository;
import com.revature.OTL.cart.Cart;
import com.revature.OTL.cart.CartRepository;
import com.revature.OTL.cart.CartService;
import com.revature.OTL.cart.dto.CartRequestDto;
import com.revature.OTL.user.UserRepo;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepo userRepo;
    private final CarRepository carRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepo userRepo, CarRepository carRepository) {
        this.cartRepository = cartRepository;
        this.userRepo = userRepo;
        this.carRepository = carRepository;
    }

    @Override
    public List<CartRequestDto> getAllCarts() {
        return cartRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<CartRequestDto> getCartById(int id) {
        return cartRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public CartRequestDto createCart(CartRequestDto cartDTO) {
        Cart cart = convertToEntity(cartDTO);
        Cart savedCart = cartRepository.save(cart);
        return convertToDto(savedCart);
    }

    @Override
    public Cart updateCart(int id, CartRequestDto cartDTO) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setOrderDate(cartDTO.getOrderDate());
                    cart.setTotalPrice(cartDTO.getTotalPrice());
                    cart.setEstDel(cartDTO.getEstDel());
                    cart.setUser(userRepo.findById(cartDTO.getUserId()).orElse(null));
                    cart.setCar(carRepository.findById(cartDTO.getCarId()).orElse(null));
                    cart.setDescription(cartDTO.getDescription());
                    return cartRepository.save(cart); // Return the updated Cart entity
                })
                .orElseGet(() -> {
                    Cart cart = convertToEntity(cartDTO); // Convert DTO to Cart entity
                    cart.setOrderId(id);
                    return cartRepository.save(cart); // Save and return the new Cart entity
                });
    }

    @Override
    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }

    private CartRequestDto convertToDto(Cart cart) {
        CartRequestDto cartDTO = new CartRequestDto();
        cartDTO.setOrderId(cart.getOrderId());
        cartDTO.setOrderDate(cart.getOrderDate());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setEstDel(cart.getEstDel());
        cartDTO.setUserId(cart.getUser().getId());
        cartDTO.setCarId(cart.getCar().getId());
        cartDTO.setDescription(cart.getDescription());
        return cartDTO;
    }

    private Cart convertToEntity(CartRequestDto cartDTO) {
        Cart cart = new Cart();
        cart.setOrderId(cartDTO.getOrderId());
        cart.setOrderDate(cartDTO.getOrderDate());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        cart.setEstDel(cartDTO.getEstDel());
        cart.setUser(userRepo.findById(cartDTO.getUserId()).orElse(null));
        cart.setCar(carRepository.findById(cartDTO.getCarId()).orElse(null));
        cart.setDescription(cartDTO.getDescription());
        return cart;
    }
}
