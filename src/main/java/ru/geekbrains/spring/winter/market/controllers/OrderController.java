package ru.geekbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.winter.market.model.OrderItemModel;
import ru.geekbrains.spring.winter.market.model.OrderModel;
import ru.geekbrains.spring.winter.market.entities.User;
import ru.geekbrains.spring.winter.market.services.OrderService;
import ru.geekbrains.spring.winter.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal /*, @RequestBody OrderData orderData */) {
        //Теперь передаю principal, и обрабатываю уже в сервисе заказов
        orderService.createOrder(principal);
    }

    @GetMapping
    public List<OrderModel> getOrders(){
        return orderService.getOrders().stream().map(OrderModel::new).collect(Collectors.toList());
    }
}
