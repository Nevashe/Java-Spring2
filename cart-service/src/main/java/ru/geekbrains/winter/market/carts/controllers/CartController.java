package ru.geekbrains.winter.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.api.CartDto;
import ru.geekbrains.winter.market.carts.convertes.CartConverter;
import ru.geekbrains.winter.market.carts.model.Cart;
import ru.geekbrains.winter.market.carts.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id, @RequestHeader(defaultValue = "") String username) {
        cartService.add(id, username);
    }

    @GetMapping("/clear")
    public void clearCart(@RequestHeader(defaultValue = "") String username) {
        cartService.clear(username);
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id, @RequestHeader(defaultValue = "") String username) {
        cartService.remove(id, username);
    }

    @GetMapping
    public CartDto getCurrentCart(@RequestHeader(defaultValue = "") String username) {
        return cartConverter.entityToDto(cartService.getCurrentCart(username));
    }
}
