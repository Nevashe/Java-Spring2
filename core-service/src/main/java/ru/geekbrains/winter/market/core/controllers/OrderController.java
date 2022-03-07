package ru.geekbrains.winter.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.api.OrderDto;
import ru.geekbrains.winter.market.core.converters.OrderConverter;
import ru.geekbrains.winter.market.core.entities.User;
import ru.geekbrains.winter.market.core.services.OrderService;
import ru.geekbrains.winter.market.core.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal /*, @RequestBody OrderData orderData */) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Пользователь не найден")); // TODO: HW FIX
        orderService.createOrder(user);
    }

    @GetMapping
    public List<OrderDto> getOrders(){
        return orderService.getOrders().stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
