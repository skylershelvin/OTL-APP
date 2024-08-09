package com.revature.OTL.cart;

import java.util.List;

import com.revature.OTL.cart.dto.CartRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartRequestDto> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartRequestDto> getCartById(@PathVariable int id) {
        return cartService.getCartById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CartRequestDto createCart(@RequestBody CartRequestDto cartRequestDto) {
        return cartService.createCart(cartRequestDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable int id, @RequestBody CartRequestDto cartDetails) {
        return ResponseEntity.ok(cartService.updateCart(id, cartDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
